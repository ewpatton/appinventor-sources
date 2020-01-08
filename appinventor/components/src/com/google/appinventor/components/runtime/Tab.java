// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2019 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.MediaUtil;

import java.io.IOException;

@DesignerComponent(version = YaVersion.TAB_COMPONENT_VERSION,
  category = ComponentCategory.LAYOUT)
@SimpleObject
public class Tab extends HVArrangement<ViewGroup> implements Component, ComponentContainer {

  private static final String LOG_TAG = Tab.class.getSimpleName();
  private final android.support.design.widget.TabLayout.Tab tab;
  private String image = "";
  private Drawable icon = null;
  private String text = "";
  private boolean showIcon = true;
  private boolean showText = true;

  public Tab(TabArrangement container) {
    super(container, HVArrangement.LAYOUT_ORIENTATION_VERTICAL, new FrameLayout(container.$context()));
    tab = container.getTabLayout().newTab();
    container.addTab(this);
  }

  public android.support.design.widget.TabLayout.Tab getTab() {
    return tab;
  }

  // MARK: Events

  @SimpleEvent
  public void Click() {
    EventDispatcher.dispatchEvent(this, "Click");
  }

  // MARK: Methods

  @SimpleFunction
  public void Show() {
    tab.select();
  }

  // MARK: Properties

  /**
   *
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void Text(String text) {
    tab.setText(text);
  }

  @SimpleProperty
  public String Text() {
    CharSequence text = tab.getText();
    this.text = text == null ? "" : text.toString();
    return this.text;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET)
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void Icon(String path) {
    try {
      icon = MediaUtil.getBitmapDrawable($form(), path);
      tab.setIcon(showIcon ? icon : null);
      image = path;
    } catch (IOException e) {
      Log.d(LOG_TAG, "Unable to load icon " + image);
    }
  }

  @SimpleProperty
  public String Icon() {
    return image;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
      defaultValue = "False")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
  public void Scrollable(boolean scrollable) {
    if (scrollable) {
      frameContainer = new ScrollView(container.$context());
    }
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
      defaultValue = "True")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void ShowImage(boolean show) {
    showIcon = show;
    tab.setIcon(show ? icon : null);
  }

  @SimpleProperty
  public boolean ShowImage() {
    return showIcon;
  }

  @DesignerProperty(editorType =  PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
      defaultValue = "True")
  @SimpleProperty(category = PropertyCategory.APPEARANCE)
  public void ShowText(boolean show) {
    showText = show;
    tab.setText(show ? text : null);
  }

  @SimpleProperty
  public boolean ShowText() {
    return showText;
  }
}
