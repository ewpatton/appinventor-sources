package com.google.appinventor.components.runtime.errors;

import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.StackFrame;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.Symbol;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.lang.Exception;

public class WrappedException extends Exception {
  private static final String LOG_TAG = "WrappedException";
  private String errorType;
  private List<StackFrame> stackTrace;

  public WrappedException(String message, String errorType) {
    super(message);
    this.errorType = errorType;
    this.stackTrace = captureStack();
  }

  public WrappedException(Exception e) throws JSONException {
    super(e);
    this.stackTrace = captureStack();
    for (StackFrame f : stackTrace) {
      Log.d(LOG_TAG, "StackFrame: " + f.toJson().toString());
    }
    LocationEnumeration it = Form.getActiveForm().getGlobalEnvironment().enumerateAllLocations();
    Log.d(LOG_TAG, "Global environment:");
    while (it.hasMoreElements()) {
      Location l = it.nextElement();
      Symbol key = l.getKeySymbol();
      Object value = l.getValue();
      Log.d(LOG_TAG, "  key = " + key);
      Log.d(LOG_TAG, "  value = " + value);
    }
  }

  public String getErrorType() {
    return errorType;
  }

  public List<StackFrame> getBlockStackTrace() {
    return Collections.unmodifiableList(stackTrace);
  }

  private static List<StackFrame> captureStack() {
    List<StackFrame> frames = new ArrayList<>();
    for (StackFrame frame : StackFrame.get()) {
      try {
        frames.add((StackFrame) frame.clone());
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
    }
    return frames;
  }
}
