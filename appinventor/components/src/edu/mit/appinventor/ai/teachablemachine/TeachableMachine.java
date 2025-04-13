// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2018 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package edu.mit.appinventor.ai.teachablemachine;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesAssets;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.OnClearListener;
import com.google.appinventor.components.runtime.OnPauseListener;
import com.google.appinventor.components.runtime.OnResumeListener;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.YailDictionary;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 * Component that classifies images using a user trained model from the teachable machine.
 * Based heavily on the Personal Image Classification Extension by data1013@mit.edu (Danny Tang)
 *
 * @author yugal1434@gmail.com (Yugal Agarwal)
 */

// Initialization of Extension
@DesignerComponent(version = 20250312,
    category = ComponentCategory.EXTENSION,
    description = "Component that classifies images using a user trained model from the image " +
        "classification explorer. You must provide a WebViewer component in the Teachable Machine Extension " +
        "component's WebViewer property in order for classification to work.",
    iconName = "aiwebres/tm.png",
    nonVisible = true)
@SimpleObject(external = true)
// Defining the assets
@UsesAssets(fileNames = "teachable_machine.html, teachable_machine.js")
@UsesPermissions("android.permission.CAMERA")
public final class TeachableMachine extends AndroidNonvisibleComponent
    implements Component, OnPauseListener, OnResumeListener, OnClearListener {

    private static final String LOG_TAG = TeachableMachine.class.getSimpleName();
    private static final Logger LOG = Logger.getLogger(TeachableMachine.class.getName());
    private static final String MODE_VIDEO = "Video";
    private static final String MODE_IMAGE = "Image";
    public static final String ERROR_WEBVIEWER_NOT_SET =
        "You must specify a WebViewer using the WebViewer designer property before you can call %1s";

    // other error codes are defined in teachable_machine.js
    private static final int ERROR_CLASSIFICATION_NOT_SUPPORTED = -1;
    public static final int ERROR_CLASSIFICATION_FAILED = -2;
    private static final int ERROR_CANNOT_TOGGLE_CAMERA_IN_IMAGE_MODE = -3;
    private static final int ERROR_CANNOT_CLASSIFY_IMAGE_IN_VIDEO_MODE = -4;
    private static final int ERROR_CANNOT_CLASSIFY_VIDEO_IN_IMAGE_MODE = -5;
    private static final int ERROR_INVALID_INPUT_MODE = -6;
    private static final int ERROR_WEBVIEWER_REQUIRED = -7;
    // below 2 are required since in PIC we import model
    private static final int ERROR_INVALID_MODEL_FILE = -8;
    private static final int ERROR_MODEL_REQUIRED = -9;

    Object webview = null;
    private String inputMode = MODE_VIDEO;
    List<String> labels = Collections.emptyList();
    String modelPath = null;
    private boolean running = false;
    // Minimum time classfier should take to load
    private int minClassTime = 0;

    // Store the latest classification result
    private YailDictionary latestClassificationResult = new YailDictionary();

    public TeachableMachine() {
        super(null);
    }

    // Setting up of Hardware and Webviewer
    public TeachableMachine(final Form form) {
        super(form);
        webViewHelper = BaseHelperFactory.getInstance().create(this);
        LOG.fine("Created TeachableMachine component");
    }


    public static final String MODEL_URL =  "https://teachablemachine.withgoogle.com/models/";

    // Web view component that camera see
    public void Initialize() {
        LOG.fine("webview = " + webview);
        if (webview == null) {
            form.dispatchErrorOccurredEvent(this, "WebViewer",
                ErrorMessages.ERROR_EXTENSION_ERROR, ERROR_WEBVIEWER_REQUIRED, LOG_TAG,
                "You must specify a WebViewer component in the WebViewer property.");
            return;
        }
        // if model link not given
        LOG.fine("modelPath = " + modelPath);

        if (modelPath == null) {
            form.dispatchErrorOccurredEvent(this, "Model",
                ErrorMessages.ERROR_EXTENSION_ERROR, ERROR_MODEL_REQUIRED, LOG_TAG,
                "You must provide a model file in the Model property");
        }
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    // Property that takes Model Link as the input
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
    @SimpleProperty(userVisible = false)
    @JsProperty(name="ModelLink")
    public void ModelLink(String link) {
        if (link.contains(MODEL_URL)) {
            modelPath = link;

        }

        else {
            form.dispatchErrorOccurredEvent(this, "ModelLink",
                ErrorMessages.ERROR_EXTENSION_ERROR, ERROR_INVALID_MODEL_FILE, LOG_TAG,
                "Incorrect Model Link: The link should look like " + MODEL_URL);
        }
    }

    private IWebViewHelper webViewHelper = null;

    // Setting up webviewer
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COMPONENT + ":com.google.appinventor.runtime.components.WebViewer")
    @SimpleProperty(userVisible = false)
    @JsProperty(name="WebViewer")
    public void WebViewer(final WebViewer webviewer) {
        webViewHelper = BaseHelperFactory.getInstance().create(TeachableMachine.this);
        Runnable next = new Runnable() {
            public void run() {
                if (webviewer != null) {
                    webViewHelper.configureWebView(webviewer.getView());

                    try {
                        webViewHelper.loadUrl(form.getAssetPathForExtension(TeachableMachine.this, "teachable_machine.html"), new Runnable() {
                            @Override
                            public void run() {
                                if (modelPath != null) {
                                    String js = "loadModel(\"" + modelPath + "\");";
                                    webViewHelper.evaluateJavascript(js);
                                }
                            }
                        });
                    } catch (Exception e) {
                        LOG.log(Level.SEVERE, "Error loading teachable_machine.html", e);
                    }

                }
            }
        };
        if (webViewHelper.shouldAskForPermission()) {
            webViewHelper.askForPermission(next);
        } else {
            next.run();
        }
    }



    // Defining Input Mode
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_CHOICES,
        editorArgs = {MODE_VIDEO, MODE_IMAGE})
    @SimpleProperty
    @JsProperty(name="InputMode")
    public void InputMode(String mode) {
        if (webview == null) {
            inputMode = mode;
            return;
        }
        if (MODE_VIDEO.equalsIgnoreCase(mode)) {
            webViewHelper.evaluateJavascript("setInputMode(\"video\");");
            inputMode = MODE_VIDEO;
        } else if (MODE_IMAGE.equalsIgnoreCase(mode)) {
            webViewHelper.evaluateJavascript("setInputMode(\"image\");");
            inputMode = MODE_IMAGE;
        } else {
            form.dispatchErrorOccurredEvent(this, "InputMode", ErrorMessages.ERROR_EXTENSION_ERROR, ERROR_INVALID_INPUT_MODE, LOG_TAG, "Invalid input mode " + mode);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR,
        description = "Gets or sets the input mode for classification. Valid values are \"Video\" " +
            "(the default) and \"Image\".")
    @JsProperty(name="InputMode")
    public String InputMode() {
        return inputMode;
    }

    // label like me or not me
    @SimpleProperty(description = "Gets all of the labels from this model. Only valid after ClassifierReady is signaled.")
    @JsProperty(name="ModelLabels")
    public List<String> ModelLabels() {
        return labels;
    }


    // return whether the classfier is running or not
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @JsProperty(name="Running")
    public boolean Running() {
        return running;
    }

    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER,
        defaultValue = "0")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    @JsProperty(name="MinimumInterval")
    public void MinimumInterval(int interval) {
        minClassTime = interval;
        if (webview != null) {
            webViewHelper.evaluateJavascript("minClassTime = " + interval + ";");
        }
    }



    //continue from here

    @SimpleProperty
    @JsProperty(name="MinimumInterval")
    public int MinimumInterval() {
        return minClassTime;
    }

    // classifying image data
    @SimpleFunction(description = "Performs classification on the image at the given path and triggers the GotClassification event when classification is finished successfully.")
    @JsMethod
    public void ClassifyImageData(final String image) {
        webViewHelper.assertWebView("ClassifyImageData");
        webViewHelper.classifyImageData(image);
    }

    // toogling camera
    @SimpleFunction(description = "Toggles between user-facing and environment-facing camera.")
    @JsMethod
    public void ToggleCameraFacingMode() {
        webViewHelper.assertWebView("ToggleCameraFacingMode");
        webViewHelper.evaluateJavascript("toggleCameraFacingMode();");

    }

    @SimpleFunction(description = "Performs classification on current video frame and triggers the GotClassification event when classification is finished successfully.")
    @JsMethod
    public void ClassifyVideoData() {
        webViewHelper.assertWebView("ClassifyVideoData");
        webViewHelper.evaluateJavascript("classifyVideoData();");

    }

    // starts classifying data[Still in Development mode]
    @SimpleFunction
    @JsMethod
    public void StartContinuousClassification() {
        if (MODE_VIDEO.equals(inputMode) && !running) {
            webViewHelper.assertWebView("StartVideoClassification");
            webViewHelper.evaluateJavascript("startVideoClassification();");
            running = true;
        }
    }

    // stops the classification[Still in Development mode]
    @SimpleFunction
    @JsMethod
    public void StopContinuousClassification() {
        if (MODE_VIDEO.equals(inputMode) && running) {
            webViewHelper.assertWebView("StopVideoClassification");
            webViewHelper.evaluateJavascript("stopVideoClassification();");
            running = false;
        }
    }

    // when classifier is ready to do classiciation
    @SimpleEvent(description = "Event indicating that the classifier is ready.")
    public void ClassifierReady() {
        InputMode(inputMode);
        MinimumInterval(minClassTime);
        EventDispatcher.dispatchEvent(this, "ClassifierReady");
    }

    // data we get after classification is done
    @SimpleEvent(description = "Event indicating that classification has finished successfully. Result is of the form [[class1, confidence1], [class2, confidence2], ..., [class10, confidence10]].")
    public void GotClassification(YailDictionary result) {
        // Store the latest result
        latestClassificationResult = result;
        EventDispatcher.dispatchEvent(this, "GotClassification", result);
    }

    @SimpleEvent(description = "Event indicating that an error has occurred.")
    public void Error(final int errorCode) {
        EventDispatcher.dispatchEvent(this, "Error", errorCode);
    }

    /**
     * GetClassification Property - returns the most likely category name from the latest result.
     *
     * @return The most likely category name as a String. Empty string if no classification yet.
     */
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the name of the most likely category from the latest classification.  Will be empty text if no classification has been performed or if the classification result is empty.")
    @JsProperty(name="Classification")
    public String Classification() {
        if (latestClassificationResult == null || latestClassificationResult.size() == 0) {
            LOG.warning("GetClassification: Classification result dictionary is empty or null.");
            return ""; // Return empty string if no result
        }

        String classifiedCategory = "";
        double maxClassificationConfidence = -1.0;

        for (Map.Entry<Object, Object> entry : latestClassificationResult.entrySet()) {
            String categoryName = (String) entry.getKey();
            double confidence = (Double) entry.getValue(); // Values are Doubles in YailDictionary in this case

            if (confidence > maxClassificationConfidence) {
                maxClassificationConfidence = confidence;
                classifiedCategory = categoryName;
            }
        }

        if (classifiedCategory.isEmpty()) {
            LOG.warning("GetClassification: No category with confidence found in dictionary.");
        } else {
            LOG.warning("GetClassification: Classified category is " + classifiedCategory + " with confidence " + maxClassificationConfidence);
        }

        return classifiedCategory;
    }

    /**
     * Confidence Property - returns the confidence score of the most likely category from the latest
     * result.
     *
     * @return The confidence score of the most likely category as a Double. 0.0 if no classification
     *     yet.
     */
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the confidence score (0.0 to 1.0) of the most likely category from the latest classification. Will be 0.0 if no classification has been performed or if the classification result is empty.")
    @JsProperty(name="Confidence")
    public double Confidence() {
        if (latestClassificationResult == null || latestClassificationResult.size() == 0) {
            LOG.warning("Confidence Property: No classification result available yet.");
            return 0.0; // Return 0.0 if no result available
        }

        double maxClassificationConfidence = 0.0; // Default to 0.0 if no category found
        for (Map.Entry<Object, Object> entry : latestClassificationResult.entrySet()) {
            double confidence = (Double) entry.getValue(); // Values are Doubles in YailDictionary in this case

            if (confidence > maxClassificationConfidence) {
                maxClassificationConfidence = confidence;
            }
        }
        return maxClassificationConfidence;
    }

    ///REGION: Lifecycle handling


    @Override
    public void onPause() {
        if (MODE_VIDEO.equals(inputMode)) {
            webViewHelper.evaluateJavascript("stopVideo();");
        }
    }

    @Override
    public void onResume() {
        if (MODE_VIDEO.equals(inputMode)) {
            webViewHelper.evaluateJavascript("startVideo();");
        }
    }


    @Override
    public void onClear() {
        webViewHelper.evaluateJavascript("stopVideo();");
    }

    ///ENDREGION


    Form getForm() {
        return form;
    }

}
