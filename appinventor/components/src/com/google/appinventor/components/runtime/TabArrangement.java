// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2019 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.YaVersion;

@DesignerComponent(version = YaVersion.TABARRANGEMENT_COMPONENT_VERSION,
  category = ComponentCategory.LAYOUT)
@SimpleObject
@UsesLibraries({"design.aar", "design.jar", "cardview-v7.aar", "cardview-v7.jar",
    "recyclerview-v7.aar", "recyclerview-v7.jar"})
public class TabArrangement extends AndroidViewComponent<ViewPager> implements ComponentContainer {
  private TabLayout layout;
  private ViewPager pager;

  /**
   * Creates a new AndroidViewComponent.
   *
   * @param container container, component will be placed in
   */
  public TabArrangement(ComponentContainer container) {
    super(container);
    pager = new ViewPager(container.$context());
    layout = new TabLayout(container.$context());
    layout.setupWithViewPager(pager);
    LayoutParams params = new LayoutParams();
    params.width = LayoutParams.MATCH_PARENT;
    params.height = LayoutParams.MATCH_PARENT;
    params.gravity = Gravity.TOP;
    pager.addView(layout, params);
    container.$add(this);
  }

  @Override
  public ViewPager getView() {
    return pager;
  }

  public TabLayout getTabLayout() {
    return layout;
  }

  void addTab(Tab tab) {
    layout.addTab(tab.getTab());
  }

  @Override
  public Activity $context() {
    return container.$context();
  }

  @Override
  public Form $form() {
    return container.$form();
  }

  @Override
  public void $add(AndroidViewComponent<? extends View> component) {
    throw new UnsupportedOperationException("Children should call addTab instead of $add");
  }

  @Override
  public void setChildWidth(AndroidViewComponent<? extends View> component, int width) {
    throw new UnsupportedOperationException("Tab children always fill parent");
  }

  @Override
  public void setChildHeight(AndroidViewComponent<? extends View> component, int height) {
    throw new UnsupportedOperationException("Tab children always fill parent");
  }

  // MARK: Events

  @SimpleEvent
  public void ShowTab(Tab tab) {
    EventDispatcher.dispatchEvent(this, "ShowTab", tab);
  }

  // MARK: Methods

  // MARK: Properties

  @Override
  @SimpleProperty
  public void Width(int width) {
    if (width == LENGTH_PREFERRED) {
      width = LENGTH_FILL_PARENT;
    }
    super.Width(width);
  }

  @Override
  @SimpleProperty
  public void Height(int height) {
    if (height == LENGTH_PREFERRED) {
      height = LENGTH_FILL_PARENT;
    }
    super.Height(height);
  }
}
