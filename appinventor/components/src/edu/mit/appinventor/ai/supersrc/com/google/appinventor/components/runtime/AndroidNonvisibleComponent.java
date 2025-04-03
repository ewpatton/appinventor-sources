package com.google.appinventor.components.runtime;

public class AndroidNonvisibleComponent implements Component {
  protected final Form form;

  protected AndroidNonvisibleComponent(Object parent) {
    if (parent != null) {
      form = (Form) parent;
    } else {
      form = new Form();
    }
  }

  public HandlesEventDispatching getDispatchDelegate() {
    // TODO(ewpatton): implement this method
    return form.getDispatchDelegate();
  }
}
