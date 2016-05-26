package com.lucidlogic.horsetracker.recyclerviewdemo.util;

import android.content.Context;

import java.io.InputStream;
import java.util.Scanner;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by itowey on 25/05/16.
 */
public class LocalFileLoader {

  public LocalFileLoader() {

  }

  /**
   * Returns an observable string which contains the contents of the specified resourceId
   *
   * @param context
   * @param resourceId
   * @return
   */
  public Observable<String> getFileAsString(final Context context, final int resourceId) {

    return Observable.create(new Observable.OnSubscribe<String>() {

      @Override
      public void call(final Subscriber<? super String> subscriber) {
        final InputStream jsonStream = context.getResources().openRawResource(resourceId);
        final String jsonString = new Scanner(jsonStream).useDelimiter("\\Z").next();
        subscriber.onNext(jsonString);
      }
    });
  }

}
