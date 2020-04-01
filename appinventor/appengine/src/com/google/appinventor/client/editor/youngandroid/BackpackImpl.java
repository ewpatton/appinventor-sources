package com.google.appinventor.client.editor.youngandroid;

import com.google.appinventor.blocklyeditor.api.BackpackSource;
import com.google.appinventor.client.OdeAsyncCallback;
import com.google.appinventor.client.output.OdeLog;
import com.google.appinventor.shared.rpc.user.UserInfoServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType
public class BackpackImpl implements BackpackSource {

  protected final UserInfoServiceAsync service;
  private String contents = "";

  @JsIgnore
  public BackpackImpl(UserInfoServiceAsync service) {
    this.service = service;
  }

  @Override
  @JsMethod
  public void setBackpackContents(String contents) {
    this.contents = contents;
    service.storeUserBackpack(contents, new OdeAsyncCallback<Void>() {
      @Override
      public void onSuccess(Void aVoid) {
        // nothing to do...
      }
    });
  }

  @Override
  @JsMethod
  public void getBackpackContents(final BackpackReceiver receiver) {
    receiver.receive(contents);
  }

  public void loadBackpack() {
    service.getUserBackpack(new AsyncCallback<String>() {
      @Override
      public void onSuccess(String backpack) {
        contents = backpack;
      }
      @Override
      public void onFailure(Throwable caught) {
        OdeLog.log("Fetching backpack failed");
      }
    });
  }
}
