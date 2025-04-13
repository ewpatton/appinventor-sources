package com.google.appinventor.components.runtime;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import jsinterop.annotations.JsType;

@JsType
public class WebViewer {
  private final DivElement container;

  public WebViewer() {
    container = Document.get().createDivElement();
    container.setId("webcam-box");
  }

  public DivElement getView() {
    return container;
  }
}
