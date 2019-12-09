// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.boxes;

import static com.google.appinventor.client.Ode.MESSAGES;
import com.google.appinventor.client.widgets.boxes.Box;

/**
 * Box implementation for debugger panel
 *
 */
public final class DebuggerBox extends Box {

  // Singleton debugger box instance
  private static final DebuggerBox INSTANCE = new DebuggerBox();

  /**
   * Return the debugger box.
   *
   * @return debugger box
   */
  public static DebuggerBox getDebuggerBox() {
    return INSTANCE;
  }

  /**
   * Creates new debugger box.
   */
  private DebuggerBox() {
    super(MESSAGES.debuggerBoxCaption(),
        300,       // height
        false,     // minimizable
        false,     // removable
        false,     // startMinimized
        false,     // usePadding
        false);    // highlightCaption
  }
}
