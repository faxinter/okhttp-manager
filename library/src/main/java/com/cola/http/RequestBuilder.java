package com.cola.http;

import android.support.annotation.NonNull;

import com.cola.http.callback.Callback;

import java.util.Map;

/**
 * @ Author SpringWater
 * @ Date 15/12/8 上午10:20
 * @ Description // Please Add Annotation
 */
public class RequestBuilder {

    private OkHttp mOkHttp;

    private String mUrl;
    private Map<String, String> mHeaders;
    private Map<String, String> mParams;
    private Callback mCallBack;

    private RequestManager mRequestManager = new RequestManager();

    public RequestBuilder(OkHttp okHttp) {
        mOkHttp = okHttp;
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

    public void get(Callback callback) {
        mCallBack = callback;
        mRequestManager.doGet(this);
    }

    public void post(Callback callback) {
        mRequestManager.doPost(this);
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
