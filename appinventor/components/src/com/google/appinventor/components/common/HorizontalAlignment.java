// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2017 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.common;

public enum HorizontalAlignment {
  _IGNORED,
  LEFT,
  RIGHT,
  CENTER;

  public static HorizontalAlignment valueOf(int ordinal) {
    try {
      return HorizontalAlignment.values()[ordinal];
    } catch(ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
