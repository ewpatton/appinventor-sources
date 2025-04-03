package edu.mit.appinventor.ai.teachablemachine;

public abstract class BaseHelperFactory {
  private static BaseHelperFactory instance;

  public static BaseHelperFactory getInstance() {
    return instance;
  }

  protected static void setInstance(BaseHelperFactory instance) {
    BaseHelperFactory.instance = instance;
  }

  public abstract IWebViewHelper create(TeachableMachine inner);
}
