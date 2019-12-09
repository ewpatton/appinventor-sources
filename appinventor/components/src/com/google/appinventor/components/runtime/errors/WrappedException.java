package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.StackFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.lang.Exception;

public class WrappedException extends Exception {
  private String errorType;
  private List<StackFrame> stackTrace;

  public WrappedException(String message, String errorType) {
    super(message);
    this.errorType = errorType;
    this.stackTrace = captureStack();
  }

  public WrappedException(Exception e) {
    super(e);
    this.stackTrace = captureStack();
  }

  public String getErrorType() {
    return errorType;
  }

  public List<StackFrame> getBlockStackTrace() {
    return Collections.unmodifiableList(stackTrace);
  }

  private static List<StackFrame> captureStack() {
    List<StackFrame> frames = new ArrayList<>();
    for (StackFrame frame : Form.getActiveForm().$getBlockStack()) {
      try {
        frames.add((StackFrame) frame.clone());
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
    }
    return frames;
  }
}
