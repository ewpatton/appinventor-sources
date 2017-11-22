// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2017 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.appinventor.components.runtime.util.RetValManager;
import gnu.expr.Language;
import gnu.mapping.Procedure;
import kawa.standard.Scheme;
import android.os.Process;

public class BlocksThread extends Thread {

  private static final String LOG_TAG = BlocksThread.class.getSimpleName();

  private Language scheme;
  private static BlocksThread theInstance = null;
  private Handler handler;
  private Looper mLooper;

  private BlocksThread() {
    super(null, null, "BlocklyThread", 32*1024);
    scheme = Scheme.getInstance("scheme");
    gnu.expr.ModuleExp.mustNeverCompile();
  }

  @Override
  public void run() {
    Looper.prepare();
    synchronized (this) {
      mLooper = Looper.myLooper();
      notifyAll();
    }
    Process.setThreadPriority(Process.THREAD_PRIORITY_FOREGROUND);
    onLooperPrepared();
    Looper.loop();
  }

  public Looper getLooper() {
    if (!isAlive()) {
      return null;
    }

    // If the thread has been started, wait until the looper has been created.
    synchronized (this) {
      while (isAlive() && mLooper == null) {
        try {
          wait();
        } catch (InterruptedException e) {
        }
      }
    }
    return mLooper;
  }

  public boolean quit() {
    Looper looper = getLooper();
    if (looper != null) {
      looper.quit();
      return true;
    }
    return false;
  }

  protected void onLooperPrepared() {
    handler = new Handler(getLooper());
    synchronized (LOG_TAG) {
      LOG_TAG.notify();
    }
  }

  public Language getScheme() {
    return scheme;
  }

  public static BlocksThread get() {
    if (theInstance == null) {
      theInstance = new BlocksThread();
      synchronized (LOG_TAG) {
        theInstance.start();
        try {
          LOG_TAG.wait();
        } catch (InterruptedException e) {
          // should never happen
        }
      }
    }
    return theInstance;
  }

  public static void runOnBlocksThreadAsync(Runnable r) {
    get().handler.post(r);
  }

  public static void runOnBlocksThreadAsync(final Procedure p) {
    runOnBlocksThreadAsync(new Runnable() {
      @Override
      public void run() {
        try {
          p.apply0();
        } catch (Throwable throwable) {
          throwable.printStackTrace();
        }
      }
    });
  }

  public static Object runOnBlocksThread(final Procedure p) throws Throwable {
    RunnableWithResult<Object> runnable = new RunnableWithResult<Object>() {
      @Override
      public void run() {
        try {
          result = p.apply0();
        } catch (Throwable t) {
          exception = t;
        }
      }
    };
    return runOnBlocksThread(runnable);
  }

  public static <T> Object runOnBlocksThread(final RunnableWithResult<T> r) throws Throwable {
    runOnBlocksThread((Runnable) r);
    if (r.getException() != null) {
      throw r.getException();
    } else {
      return r.getResult();
    }
  }

  public static void runOnBlocksThread(final Runnable r) {
    final Object lock = new Object();
    synchronized (lock) {
      get().handler.post(new Runnable() {
        @Override
        public void run() {
          try {
            r.run();
          } finally {
            synchronized (lock) {
              lock.notifyAll();
            }
          }
        }
      });
      try {
        lock.wait();
      } catch (InterruptedException e) {
        Log.e(LOG_TAG, "Blocks evaluation interrupted.", e);
        get().quit();
      }
    }
  }

  public static void eval(final String sexpr) {
    runOnBlocksThread(new Runnable() {
      @Override
      public void run() {
        try {
          get().scheme.eval(sexpr);
        } catch (Throwable t) {
          Log.e(LOG_TAG, "", t);
          RetValManager.sendError(t.toString());
        }
      }
    });
  }

  public static Object runOnUiThreadSync(final Procedure p) throws Throwable {
    final Form lock = Form.getActiveForm();
    synchronized (lock) {
      final boolean onUIThread = Looper.getMainLooper().getThread().equals(Thread.currentThread());
      RunnableWithResult<Object> runnable = wrap(p, onUIThread, lock);
      lock.runOnUiThread(runnable);
      if (!onUIThread) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      if (runnable.getException() != null) {
        throw runnable.getException();
      } else {
        return runnable.getResult();
       }
    }
  }

  private static RunnableWithResult<Object> wrap(final Procedure p, final boolean onUIThread, final Object lock) {
    return new RunnableWithResult<Object>() {
      @Override
      public void run() {
        try {
          result = p.apply0();
        } catch (Throwable throwable) {
          exception = throwable;
        } finally {
          if (!onUIThread) {
            synchronized (lock) {
              lock.notifyAll();
            }
          }
        }
      }
    };
  }

  private static abstract class RunnableWithResult<T> implements Runnable {
    protected T result;
    protected Throwable exception = null;

    public RunnableWithResult() {
      result = null;
    }

    public RunnableWithResult(T initial) {
      result = initial;
    }

    public T getResult() {
      return result;
    }

    public Throwable getException() {
      return exception;
    }
  }
}
