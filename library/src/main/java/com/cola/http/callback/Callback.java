package com.cola.http.callback;

/**
 * @ Author SpringWater
 * @ Date 15/12/7 下午11:00
 * @ Description // Please Add Annotation
 */
public interface Callback<T> {

    void onSuccess(T response);

    void onFailure(int statusCode, Throwable throwable);
}
