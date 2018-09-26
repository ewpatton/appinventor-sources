// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2017 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.youngandroid;

import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class TutorialPanel extends Frame {
  static {
    exportMethodsToJavascript();
    // For some reason I have not been able to discern, setting
    // autoclose on the dialogs below does not allow them to be closed
    // with the Escape key. Instead, we bind a native event preview
    // here to handle the escape key when it comes from the GWT
    // document.
    Event.addNativePreviewHandler(new NativePreviewHandler() {
      @Override
      public void onPreviewNativeEvent(NativePreviewEvent event) {
        if (event.getTypeInt() == Event.ONKEYUP &&
            event.getNativeEvent().getKeyCode() == 27) {
          hideDialog();
        }
      }
    });
  }

  private static DialogBox currentDialog = null;

  /**
   * Creates video on page!
   */
  private static void createVideoDialog(String tutorialId) {
    // Create the UI elements of the DialogBox
    final DialogBox dialogBox = new DialogBox(true, true); // DialogBox(autohide, modal)
    dialogBox.setStylePrimaryName("ode-DialogBox");
    dialogBox.setText("Tutorial Video");
    dialogBox.setGlassEnabled(true);
    dialogBox.setAnimationEnabled(true);
    VerticalPanel DialogBoxContents = new VerticalPanel();
    // Adds Youtube Video
    HTML message = new HTML("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/" + tutorialId + "?rel=0&autoplay=1\" frameborder=\"0\" allowfullscreen></iframe>");
    message.setStyleName("DialogBox-message");
    FlowPanel holder = new FlowPanel();
    Button ok = new Button("Close");
    ok.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          dialogBox.hide();
        }
      });
    ok.setStyleName("DialogBox-button");
    holder.add(ok);
    DialogBoxContents.add(message);
    DialogBoxContents.add(holder);
    dialogBox.setWidget(DialogBoxContents);
    dialogBox.center();
    dialogBox.show();
    currentDialog = dialogBox;
  }

  /**
   * Enlarges image on page
   */
  private static void createImageDialog(String img) {
    // Create the UI elements of the DialogBox
    final DialogBox dialogBox = new DialogBox(true, true); // DialogBox(autohide, modal)
    dialogBox.setStylePrimaryName("ode-DialogBox");
    dialogBox.setGlassEnabled(true);
    dialogBox.setAnimationEnabled(true);
    VerticalPanel DialogBoxContents = new VerticalPanel();
    FlowPanel holder = new FlowPanel();
    Button ok = new Button("Close");
    ok.addClickListener(new ClickListener() {
        public void onClick(Widget sender) {
          dialogBox.hide();
        }
      });
    ok.setStyleName("DialogBox-button");

    // Adds Image
    Image image = new Image(img);
    image.addLoadHandler(new LoadHandler() {
        public void onLoad(LoadEvent evt) {
          dialogBox.center();
        }
      });

    image.setStyleName("DialogBox-image");
    holder.add(ok);
    DialogBoxContents.add(image);
    DialogBoxContents.add(holder);
    dialogBox.setWidget(DialogBoxContents);
    dialogBox.center();
    dialogBox.show();
    currentDialog = dialogBox;
  }

  public static void getTutorialDialog(String tutorialId) {
    createVideoDialog(tutorialId);
  }

  public static void getImageDialog(String img) {
    createImageDialog(img);
  }

  public static void hideDialog() {
    if (currentDialog != null) {
      currentDialog.hide();
      currentDialog = null;
    }
  }

  private static native void exportMethodsToJavascript() /*-{
    $wnd.TutorialPanel_createTutorialDialog =
    $entry(@com.google.appinventor.client.editor.youngandroid.TutorialPanel::getTutorialDialog(Ljava/lang/String;));
    $wnd.TutorialPanel_createImageDialog =
    $entry(@com.google.appinventor.client.editor.youngandroid.TutorialPanel::getImageDialog(Ljava/lang/String;));
    $wnd.TutorialPanel_hideDialog =
      $entry(@com.google.appinventor.client.editor.youngandroid.TutorialPanel::hideDialog());
    $wnd.recieveMessage=function(event){
      if (event.data.type == "video") {
        $wnd.TutorialPanel_createTutorialDialog(event.data.youtubeId);
      }
    };
    $wnd.addEventListener("message", $wnd.recieveMessage, false);
    $wnd.recieveMessage=function(event){
      if (event.data.type == "img") {
        $wnd.TutorialPanel_createImageDialog(event.data.imageId);
      }
    };
    $wnd.addEventListener("message", $wnd.recieveMessage, false);
    $wnd.addEventListener("message", function(e) {
      if (e.data.type == 'close') {
        $wnd.TutorialPanel_hideDialog();
      }
    }, false);
  }-*/;
}
