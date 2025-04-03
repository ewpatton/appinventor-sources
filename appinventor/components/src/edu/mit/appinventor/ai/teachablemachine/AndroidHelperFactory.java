package edu.mit.appinventor.ai.teachablemachine;

public class AndroidHelperFactory extends BaseHelperFactory {
  static {
    // Set the instance to this class
    BaseHelperFactory.setInstance(new AndroidHelperFactory());
  }

  @Override
  public IWebViewHelper create(TeachableMachine inner) {
    return new AndroidWebViewHelper(inner);
  }
}
