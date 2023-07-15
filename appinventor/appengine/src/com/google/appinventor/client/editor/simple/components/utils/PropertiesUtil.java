// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2022 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.simple.components.utils;

import static com.google.appinventor.components.common.PropertyTypeConstants.PROPERTY_TYPE_COMPONENT;

import com.google.appinventor.client.editor.simple.components.MockComponent;
import com.google.appinventor.client.editor.simple.components.MockForm;
import com.google.appinventor.client.editor.youngandroid.palette.YoungAndroidPalettePanel;

import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidAccelerometerSensitivityChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidAlignmentChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidAssetSelectorPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidBooleanPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidButtonShapeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidChartLineTypeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidChartPointShapeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidChartTypeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidColorChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidComponentSelectorPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidDataColumnSelectorProperty;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidDefaultURLPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidFileScopePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidFloatRangePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidFontTypefaceChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidGeoJSONPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidGeographicPointPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidHorizontalAlignmentChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidIntegerRangePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidLegoEv3ColorSensorModeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidLegoEv3GyroSensorModeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidLegoEv3SensorPortChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidLegoEv3UltrasonicSensorModeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidLegoNxtSensorPortChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidLengthPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidListViewAddDataPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidListViewLayoutChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidMapScaleUnitsPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidMapTypePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidNavigationMethodChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidRecyclerViewOrientationPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidScreenAnimationChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidScreenOrientationChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidSensorDistIntervalChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidSensorTimeIntervalChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidSizingChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidTextReceivingPropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidThemeChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidToastLengthChoicePropertyEditor;
import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidVerticalAlignmentChoicePropertyEditor;

import com.google.appinventor.client.properties.BadPropertyEditorException;
import com.google.appinventor.client.widgets.properties.ChoicePropertyEditor;
import com.google.appinventor.client.widgets.properties.CountryChoicePropertyEditor;
import com.google.appinventor.client.widgets.properties.EditableProperties;
import com.google.appinventor.client.widgets.properties.EditableProperty;
import com.google.appinventor.client.widgets.properties.FloatPropertyEditor;
import com.google.appinventor.client.widgets.properties.IntegerPropertyEditor;
import com.google.appinventor.client.widgets.properties.LanguageChoicePropertyEditor;
import com.google.appinventor.client.widgets.properties.NonNegativeFloatPropertyEditor;
import com.google.appinventor.client.widgets.properties.NonNegativeIntegerPropertyEditor;
import com.google.appinventor.client.widgets.properties.PropertyEditor;
import com.google.appinventor.client.widgets.properties.ScalingChoicePropertyEditor;
import com.google.appinventor.client.widgets.properties.StringPropertyEditor;
import com.google.appinventor.client.widgets.properties.SubsetJSONPropertyEditor;
import com.google.appinventor.client.widgets.properties.TextAreaPropertyEditor;
import com.google.appinventor.client.widgets.properties.TextPropertyEditor;

import com.google.appinventor.components.common.PropertyTypeConstants;

import com.google.appinventor.shared.simple.ComponentDatabaseInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Provides utility functions for dealing with the UI for component properties.
 *
 * <p>These functions are called by descendants of MockComponent with its protected
 * field "properties" as an argument. This EditableProperties object is populated
 * by {@link YoungAndroidPalettePanel#configureComponent(MockComponent)}, which
 * calls {@link MockComponent#addProperty(String, String, String)}
 * for each of the MockComponent's properties. The addProperty method in turn calls
 * {@link EditableProperties#addProperty(String, String, String, int)},
 * adding the property to "properties".
 *
 * @author hal@mit.edu (Hal Abelson)
 */
public class PropertiesUtil {
  // Construct a HashSet of acceptable Chart Data Source components
  private static final HashSet<String> CHART_DATA_SOURCES = new HashSet<String>() {{
      add("DataFile");
      add("TinyDB");
      add("CloudDB");
      add("AccelerometerSensor");
      add("BluetoothClient");
      add("Web");
      add("GyroscopeSensor");
      add("OrientationSensor");
      add("ProximitySensor");
      add("Pedometer");
      add("LocationSensor");
      add("Spreadsheet");
    }};


  private static final Map<String, List<PropertyEditor>> cachedEditors
      = new HashMap<>();
  private static final Map<String, Integer> counts
      = new HashMap<>();

  /**
   * Prevent instantiation.
   */
  private PropertiesUtil() {
  }

  /**
   * Populate properties of a MockComponent, given a list of property definitions.
   *
   * @param mockComponent The component being used to populate the properties
   * @param propertyDefinitions The definitions of the properties to populate
   */
  public static void populateProperties(MockComponent mockComponent,
      List<ComponentDatabaseInterface.PropertyDefinition> propertyDefinitions) {

    // Configure properties
    for (ComponentDatabaseInterface.PropertyDefinition property : propertyDefinitions) {
      mockComponent.addProperty(property);
    }

  }

  // Use individual methods for each property since we can't write the generic
  // getExistingPropertyEditor due to type safety issues - see below.

  /**
   * Retrieves the property editor for Horizontal Alignment.
   *
   * @param  properties The properties set for a component
   * @return the property editor for Horizontal Alignment
   * @throws BadPropertyEditorException if the property is defined but does not use a
   *     YoungAndroidHorizontalAlignmentChoicePropertyEditor
   */
  public static YoungAndroidHorizontalAlignmentChoicePropertyEditor
      getHAlignmentEditor(EditableProperties properties)
      throws BadPropertyEditorException {

    PropertyEditor propEditor = null;

    // Get the property with the specified name from the properties
    // object. This works because an EditableProperties contains a
    // TreeMap<String, T extends Property> that maps a property's name to its instance.
    EditableProperty prop = properties.getProperty(MockForm.PROPERTY_NAME_HORIZONTAL_ALIGNMENT);

    if (prop != null) {
      // Cast prop to an EditableProperty and get its editor
      propEditor = prop.getEditor();

      // Make sure we have the right editor
      if (propEditor != null) {
        if (! (propEditor instanceof YoungAndroidHorizontalAlignmentChoicePropertyEditor)) {
          throw new BadPropertyEditorException("Bad property editor");
        }
      } else {
        return (YoungAndroidHorizontalAlignmentChoicePropertyEditor) getPropertyEditor(prop);
      }
    }

    // Cast propEditor to the appropriate type
    return (YoungAndroidHorizontalAlignmentChoicePropertyEditor)propEditor;
  }

  /**
   * Retrieves the property editor for Vertical Alignment.
   * Note that this is almost identical to {@link #getHAlignmentEditor(EditableProperties)}
   * in terms of implementation.
   *
   * @param  properties The properties set for a component
   * @return the property editor for Vertical Alignment
   * @throws BadPropertyEditorException if the property is defined but does not use a
   *     YoungAndroidVerticalAlignmentChoicePropertyEditor
   */
  public static YoungAndroidVerticalAlignmentChoicePropertyEditor
      getVAlignmentEditor(EditableProperties properties)
      throws BadPropertyEditorException {

    PropertyEditor propEditor = null;
    EditableProperty prop = properties.getProperty(MockForm.PROPERTY_NAME_VERTICAL_ALIGNMENT);
    if (prop != null) {
      propEditor = prop.getEditor();
      if (propEditor != null) {
        if (! (propEditor instanceof YoungAndroidVerticalAlignmentChoicePropertyEditor)) {
          throw new BadPropertyEditorException("Bad property editor");
        }
      } else {
        return (YoungAndroidVerticalAlignmentChoicePropertyEditor) getPropertyEditor(prop);
      }
    }
    return (YoungAndroidVerticalAlignmentChoicePropertyEditor)propEditor;
  }

  /**
   * Get an editor for the given property.
   *
   * @param property The property to be edited
   * @return A property editor corresponding to the property.
   */
  public static PropertyEditor getPropertyEditor(EditableProperty property) {
    String editorType = property.getDefinition().getEditorType();
    String[] editorArgs = property.getDefinition().getEditorArgs();
    Integer count = counts.get(editorType);
    if (count == null) {
      count = 0;
    }
    List<PropertyEditor> editors = cachedEditors.get(editorType);
    if (editors == null) {
      editors = new ArrayList<>();
      cachedEditors.put(editorType, editors);
      count = 0;
    }
    PropertyEditor editor;
    if (count == editors.size()) {
      editor = doCreatePropertyEditor(editorType, editorArgs);
      editors.add(editor);
      counts.put(editorType, editors.size());
    } else {
      editor = editors.get(count);
      counts.put(editorType, count + 1);
    }
    editor.setProperty(property);
    return editor;
  }

  public static void willRefreshProperties() {
    counts.clear();
  }

  /*
   * Creates a new property editor.
   */
  private static PropertyEditor doCreatePropertyEditor(String editorType, String[] editorArgs) {
    if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_HORIZONTAL_ALIGNMENT)) {
      return new YoungAndroidHorizontalAlignmentChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_VERTICAL_ALIGNMENT)) {
      return new YoungAndroidVerticalAlignmentChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_ASSET)) {
      return new YoungAndroidAssetSelectorPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_BLUETOOTHCLIENT)) {
      return new YoungAndroidComponentSelectorPropertyEditor(
          // Pass the set of component types that will be shown in the property editor,
          // in this case, just "BluetoothClient".
          Collections.singleton("BluetoothClient"));
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN)) {
      return new YoungAndroidBooleanPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_BUTTON_SHAPE)) {
      return new YoungAndroidButtonShapeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
      return new YoungAndroidColorChoicePropertyEditor();
    } else if (editorType.equals(PROPERTY_TYPE_COMPONENT)) {
      return new YoungAndroidComponentSelectorPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_FILESCOPE)) {
      return new YoungAndroidFileScopePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_FLOAT)) {
      return new FloatPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_GEOGRAPHIC_POINT)) {
      return new YoungAndroidGeographicPointPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_GEOJSON_TYPE)) {
      return new YoungAndroidGeoJSONPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SUBSET_JSON)) {
      return new SubsetJSONPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_INTEGER)) {
      return new IntegerPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LATITUDE)) {
      return new YoungAndroidFloatRangePropertyEditor(-90, 90);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LEGO_EV3_SENSOR_PORT)) {
      return new YoungAndroidLegoEv3SensorPortChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LEGO_EV3_COLOR_SENSOR_MODE)) {
      return new YoungAndroidLegoEv3ColorSensorModeChoicePropertyEditor();
    } else if (editorType.equals(
        PropertyTypeConstants.PROPERTY_TYPE_LEGO_EV3_ULTRASONIC_SENSOR_MODE)) {
      return new YoungAndroidLegoEv3UltrasonicSensorModeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LEGO_EV3_GYRO_SENSOR_MODE)) {
      return new YoungAndroidLegoEv3GyroSensorModeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_CHOICES)) {
      return new ChoicePropertyEditor(editorArgs);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LEGO_NXT_SENSOR_PORT)) {
      return new YoungAndroidLegoNxtSensorPortChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LEGO_NXT_GENERATED_COLOR)) {
      return new YoungAndroidColorChoicePropertyEditor(
          YoungAndroidColorChoicePropertyEditor.NXT_GENERATED_COLORS);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LENGTH)) {
      return new YoungAndroidLengthPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LONGITUDE)) {
      return new YoungAndroidFloatRangePropertyEditor(-180, 180);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_MAP_TYPE)) {
      return new YoungAndroidMapTypePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_MAP_UNIT_SYSTEM)) {
      return new YoungAndroidMapScaleUnitsPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_MAP_ZOOM)) {
      return new YoungAndroidIntegerRangePropertyEditor(1, 18);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_NAVIGATION_METHOD)) {
      return new YoungAndroidNavigationMethodChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_FLOAT)) {
      return new NonNegativeFloatPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER)) {
      return new NonNegativeIntegerPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SCREEN_ORIENTATION)) {
      return new YoungAndroidScreenOrientationChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SCREEN_ANIMATION)) {
      return new YoungAndroidScreenAnimationChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SENSOR_DIST_INTERVAL)) {
      return new YoungAndroidSensorDistIntervalChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SENSOR_TIME_INTERVAL)) {
      return new YoungAndroidSensorTimeIntervalChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_STRING)) {
      return new StringPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TEXTALIGNMENT)) {
      return new YoungAndroidAlignmentChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TEXTAREA)) {
      return new TextAreaPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_THEME)) {
      return new YoungAndroidThemeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TOAST_LENGTH)) {
      return new YoungAndroidToastLengthChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TYPEFACE)) {
      return new YoungAndroidFontTypefaceChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_VISIBILITY)) {
      return new YoungAndroidBooleanPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TEXT_RECEIVING)) {
      return new YoungAndroidTextReceivingPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_ACCELEROMETER_SENSITIVITY)) {
      return new YoungAndroidAccelerometerSensitivityChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TEXT_TO_SPEECH_COUNTRIES)) {
      return new CountryChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_TEXT_TO_SPEECH_LANGUAGES)) {
      return new LanguageChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SIZING)) {
      return new YoungAndroidSizingChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_SCALING)) {
      return new ScalingChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_FIREBASE_URL)) {
      return new YoungAndroidDefaultURLPropertyEditor("DEFAULT");
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LISTVIEW_ADD_DATA)) {
      return new YoungAndroidListViewAddDataPropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_LISTVIEW_LAYOUT)) {
      return new YoungAndroidListViewLayoutChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_X_DIM_MEASURE)
        || editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_Y_DIM_MEASURE)) {
      return new YoungAndroidLengthPropertyEditor();
    } else if (editorType.startsWith(PropertyTypeConstants.PROPERTY_TYPE_COMPONENT + ":")) {
      String type = editorType.substring(PROPERTY_TYPE_COMPONENT.length() + 2);
      type = type.substring(type.lastIndexOf('.') + 1);
      return new YoungAndroidComponentSelectorPropertyEditor(Collections.singleton(type));
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_CHART_TYPE)) {
      return new YoungAndroidChartTypeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_DATA_FILE_COLUMN)) {
      return new YoungAndroidDataColumnSelectorProperty();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_CHART_DATA_SOURCE)) {
      return new YoungAndroidComponentSelectorPropertyEditor(CHART_DATA_SOURCES);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_CHART_PIE_RADIUS)) {
      return new YoungAndroidIntegerRangePropertyEditor(0, 100);
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_CHART_POINT_SHAPE)) {
      return new YoungAndroidChartPointShapeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_CHART_LINE_TYPE)) {
      return new YoungAndroidChartLineTypeChoicePropertyEditor();
    } else if (editorType.equals(PropertyTypeConstants.PROPERTY_TYPE_RECYCLERVIEW_ORIENTATION)) {
      return new YoungAndroidRecyclerViewOrientationPropertyEditor(); 
    } else {
      return new TextPropertyEditor();
    }
  }
}
