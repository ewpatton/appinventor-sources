package com.google.appinventor.components.runtime.util;

import gnu.mapping.Procedure;

public abstract class ProcedureProxy extends Procedure {
  private Procedure lambda;

  protected ProcedureProxy() {}

  protected ProcedureProxy(Procedure lambda) {
    this.lambda = lambda;
  }

  public void wrap(Procedure lambda) {
    this.lambda = lambda;
  }

  @Override
  public Object applyN(Object[] objects) throws Throwable {
    return lambda.applyN(objects);
  }

  @Override
  public Object apply0() throws Throwable {
    return lambda.apply0();
  }

  @Override
  public Object apply1(Object o) throws Throwable {
    return lambda.apply1(o);
  }

  @Override
  public Object apply2(Object o, Object o1) throws Throwable {
    return lambda.apply2(o, o1);
  }

  @Override
  public Object apply3(Object o, Object o1, Object o2) throws Throwable {
    return lambda.apply3(o, o1, o2);
  }

  @Override
  public Object apply4(Object o, Object o1, Object o2, Object o3) throws Throwable {
    return lambda.apply4(o, o1, o2, o3);
  }

  public Object apply(Object... args) throws Throwable {
    return lambda.applyN(args);
  }
}
