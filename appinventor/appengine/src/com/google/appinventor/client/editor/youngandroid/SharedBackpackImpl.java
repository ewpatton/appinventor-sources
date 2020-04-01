package com.google.appinventor.client.editor.youngandroid;

import com.google.appinventor.client.OdeAsyncCallback;
import com.google.appinventor.shared.rpc.user.UserInfoServiceAsync;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType
public class SharedBackpackImpl extends BackpackImpl {

  private final String backpackId;

  @JsIgnore
  public SharedBackpackImpl(UserInfoServiceAsync service, String backpackId) {
    super(service);
    this.backpackId = backpackId;
  }

  @Override
  @JsMethod
  public void setBackpackContents(String contents) {
    service.storeSharedBackpack(backpackId, contents, new OdeAsyncCallback<Void>() {
      @Override
      public void onSuccess(Void aVoid) {
        // nothing to do...
      }
    });
  }

  @Override
  @JsMethod
  public void getBackpackContents(final BackpackReceiver receiver) {
    service.getSharedBackpack(backpackId, new OdeAsyncCallback<String>() {
      @Override
      public void onSuccess(String contents) {
        receiver.receive(contents);
      }
    });
  }
}
