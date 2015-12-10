package com.cola.http.callback.impl;

import com.cola.http.callback.Callback;

/**
 * @ Author SpringWater
 * @ Date 15/12/7 下午11:26
 * @ Description // Please Add Annotation
 */
public class ResultCallback<T> implements Callback<T> {

    @Override
    public void onSuccess(T response) {

    }

    @Override
    public void onFailure(int statusCode, Throwable throwable) {

    }
}
