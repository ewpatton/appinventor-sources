package edu.mit.appinventor.ai.teachablemachine.client;

import edu.mit.appinventor.ai.teachablemachine.BaseHelperFactory;
import edu.mit.appinventor.ai.teachablemachine.IWebViewHelper;
import edu.mit.appinventor.ai.teachablemachine.TeachableMachine;

public class BrowserHelperFactory extends BaseHelperFactory {
  public static void setup() {
    // This is a static block to ensure that the class is loaded and the static initializer is executed.
    setInstance(new BrowserHelperFactory());
  }

  @Override
  public IWebViewHelper create(TeachableMachine inner) {
    return new BrowserWebViewHelper(inner);
  }
}
