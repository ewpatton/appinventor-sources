// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2019 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.utils;

import com.google.appinventor.client.Ode;
import com.google.appinventor.client.OdeAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import java.util.function.Consumer;

/**
 * The <b>Promise</b> object represents the eventual completion (or failure) of an asynchronous
 * operation, and its resulting value. This Java code uses GWT's JsInterop feature to expose the
 * browser-native Promise implementation for use in Java code.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 * @param <T> the type the Promise's resolve function will pass
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise">Promise on MDN</a>
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Promise<T> {

  /**
   * Interface to represent a callback that will receive a resolved value from
   * a Promise.
   *
   * @param <T> the type of the resolved value
   */
  @JsFunction
  public interface PromiseCallback<T> {
    Object resolved(T response);
  }

  /**
   * Interface to represent a callback that will receive an error when a Promise
   * is rejected.
   */
  @JsFunction
  public interface PromiseErrorCallback {
    Object rejected(WrappedException t);
  }

  /**
   * Interface representing the Promise's native resolve callback.
   *
   * @param <T> the type of the resolved value
   */
  @JsFunction
  public interface ResolveCallback<T> {
    void apply(T arg);
  }

  /**
   * Interface representing the Promise's native reject callback.
   */
  @JsFunction
  public interface RejectCallback {
    void apply(WrappedException arg);
  }

  /**
   *
   * <b>We can replaces this with Callback with GWT 2.8.2.</b>
   *
   * @param <T> the type of the resolved value
   */
  @FunctionalInterface
  @JsFunction
  public interface PromiseConstructorCallback<T> {
    void call(ResolveCallback<T> resolve, RejectCallback reject);
  }

  /**
   * CancellationException is a helper exception to short-circuit the remainder
   * of a Promise chain in the event that some precondition fails earlier in the
   * chain.
   */
  @JsType
  public static class CancellationException extends IllegalStateException {
    @SuppressWarnings("unused")  // Needed by Serializable
    CancellationException() {
      this("");
    }

    CancellationException(String reason) {
      super(reason);
    }
  }

  /**
   * WrappedException wraps an arbitrary exception as a RuntimeException so that
   * it can be passed back through the JSNI layer.
   */
  @JsType
  public static class WrappedException extends RuntimeException {
    final Throwable original;

    @SuppressWarnings("unused")  // Needed by Serializable
    WrappedException() {
      this(null);
    }

    @JsConstructor
    WrappedException(Throwable t) {
      original = t;
    }

    /**
     * Retrieves the underlying exception wrapped by this exception.
     * @return the underlying exception
     */
    public Throwable getOriginal() {
      return original;
    }
  }

  /**
   * AsyncCallbackProxy provides an interface between the GWT AsyncCallback
   * interface used for RPC and Promises.
   *
   * @param <T> the type of the resolved value
   */
  private static class AsyncCallbackProxy<T> extends OdeAsyncCallback<T> {
    private ResolveCallback<T> resolve;
    private RejectCallback reject;

    AsyncCallbackProxy(String message) {
      super(message);
    }

    @Override
    public void onFailure(Throwable caught) {
      // This conditional is required otherwise we will show the stale session
      // dialog, but in reality the user isn't logged in yet.
      if (Ode.getInstance().getUser() != null) {
        super.onFailure(caught);
      }
      reject.apply(new WrappedException(caught));
    }

    @Override
    public void onSuccess(T result) {
      resolve.apply(result);
    }
  }

  /**
   * Constructs a new JavaScript Promise. The resolve and return callbacks will
   * be passed to the function <code>promised</code>. Note that the body is
   * intentionally empty as GWT will turn this into a call to the native Promise
   * constructor.
   *
   * @param promised the callback for the native Promise constructor
   */
  @JsConstructor
  @SuppressWarnings("unused")
  public Promise(PromiseConstructorCallback<T> promised) {
  }

  /**
   * Converts a lambda taking an AsyncCallback into a Promise.
   *
   * @param errorMessage the error message to show if the RPC fails
   * @param consumer     the lambda function accepting the AsyncCallback
   * @param <T>          the return type of the AsyncCallback, also used as the
   *                     resolved type of the promise
   * @return a new Promise that will resolve to the value of the RPC if
   *         successful or rejected if the RPC fails.
   */
  @JsOverlay
  public static <T> Promise<T> call(String errorMessage, Consumer<AsyncCallback<T>> consumer) {
    final AsyncCallbackProxy<T> callback = new AsyncCallbackProxy<>(errorMessage);
    return new Promise<>((resolve, reject) -> {
      callback.resolve = resolve;
      callback.reject = reject;
      consumer.accept(callback);
    });
  }

  /**
   * Add a then chain to the promise. Because of how JsInterop works, we cannot
   * provide the overloaded version with two arguments. Instead, call
   * {@link #error(PromiseErrorCallback)} to attach an error condition.
   *
   * @param callback the callback to receive the resolved value of the promise
   * @param <U>      used to specify an alternative return type if the callback
   *                 return a new promise resolving to a different type.
   * @return a promise that resolves to the new promise as returned by callback
   *         or to a specific value of type U returned by the callback.
   */
  public native final <U> Promise<U> then(PromiseCallback<T> callback);

  /**
   * Add an error handler (catch in JavaScript) to the promise. Because Java
   * does not allow for catch to be used as an identifier, this method is called
   * error but mapped to JavaScript Promise's catch method.
   *
   * @param callback the callback to receive the reject reason of the promise
   * @param <U>      used to specify an alternative return type if the callback
   *                 return a new promise resolving to a different type.
   * @return a promise that resolves to the new promise as returned by callback
   *         or to a specific value of type U returned by the callback.
   */
  @JsMethod(name = "catch")
  public native final <U> Promise<U> error(PromiseErrorCallback callback);

  /**
   * Add a handler that will be invoked regardless of whether the promise is
   * resolved or rejected. The callback will need to test the type of the value
   * to determine the state of the promise (if needed).
   *
   * @param callback the callback to receive the result of the promise
   * @param <U>      used to specify an alternative return type if the callback
   *                 return a new promise resolving to a different type.
   * @return a promise that resolves to the new promise as returned by callback
   *         or to a specific value of type U returned by the callback.
   */
  @JsMethod(name = "finally")
  public native final <U> Promise<U> done(PromiseCallback<Object> callback);

  /**
   * Constructs a new Promise object resolved to the given value.
   *
   * @param value the resolved value
   * @param <T>   the type of the resolved value
   * @return a resolved promise
   */
  @JsMethod
  public static native <T> Promise<T> resolve(T value);

  /**
   * Constructs a new Promise object rejected with the given reason.
   *
   * @param reason the reason for rejection
   * @param <T>    the type of the rejection
   * @return a rejected promise
   */
  @JsMethod
  public static native <T> Promise<T> reject(T reason);

  /**
   * Constructs a new Promise object rejected with the given message.
   *
   * @param reason the reason for rejection
   * @return a rejected promise
   */
  @JsOverlay
  public static Promise<CancellationException> rejectWithReason(String reason) {
    return Promise.reject(new CancellationException(reason));
  }
}
