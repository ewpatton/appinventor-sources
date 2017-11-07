package com.google.appinventor.components.scripts;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

public class EnumProcessor extends AbstractProcessor {
  private int pass = 0;

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    pass++;
    if (pass > 1) {
      return true;
    }
    return true;
  }
}
