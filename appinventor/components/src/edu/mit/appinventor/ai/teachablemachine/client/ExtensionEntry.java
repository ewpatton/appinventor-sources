package edu.mit.appinventor.ai.teachablemachine.client;

import com.google.gwt.core.client.EntryPoint;
import edu.mit.appinventor.ai.teachablemachine.TeachableMachine;

public class ExtensionEntry implements EntryPoint {
  @Override
  public void onModuleLoad() {
    BrowserHelperFactory.setup();
    ExtensionFactory.registerExtension("TeachableMachine", TeachableMachine::new);
  }
}
