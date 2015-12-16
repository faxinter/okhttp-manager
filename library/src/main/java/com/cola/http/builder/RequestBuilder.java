package com.cola.http.builder;

import android.support.annotation.NonNull;

import com.cola.http.RequestManager;
import com.cola.http.callback.Callback;

import java.util.Map;

/**
 * @ Author SpringWater
 * @ Date 15/12/8 上午10:20
 * @ Description // Please Add Annotation
 */
public abstract class RequestBuilder {

    private String mUrl;
    private Map<String, String> mHeaders;
    private Map<String, String> mParams;
    private Callback mCallBack;

    // timeout 、ua 可以进行全局设置，不需要每次请求设置
    private static int mTimeOut;
    private static String mUserAgent;

    private RequestManager mRequestManager;

    public RequestBuilder(RequestManager requestManager) {
        mRequestManager = requestManager;
    }

    public RequestBuilder url(@NonNull String url) {
        mUrl = url;
        return this;
    }

    public RequestBuilder params(Map<String, String> params) {
        mParams = params;
        return this;
    }

    public RequestBuilder headers(Map<String, String> params) {
        mHeaders = params;
        return this;
    }

    public void execute(Callback callback) {
        mCallBack = callback;
        mRequestManager.doRequest(this);
    }

    public Callback getCallBack() {
        return mCallBack;
    }

    public String getUrl() {
        return mUrl;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public Map<String, String> getParams() {
        return mParams;
    }
}
