package com.cola.http.callback.impl;

import com.cola.http.callback.Callback;

/**
 * @ Author SpringWater
 * @ Date 15/12/14 下午10:41
 * @ Description // Please Add Annotation
 */
public abstract class JsonListResultCallback<T> implements Callback<T> {

    @Override
    public void onSuccess(T response) {

    }

    @Override
    public void onFailure(int statusCode, Throwable throwable) {

    }
}
