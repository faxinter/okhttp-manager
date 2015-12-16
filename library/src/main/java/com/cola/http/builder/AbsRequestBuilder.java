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
public abstract class AbsRequestBuilder {
    protected RequestManager mRequestManager;
    protected String mUrl;
    protected Object mTag;
    protected Map<String, String> mHeaders;
    protected Map<String, String> mParams;
    protected Callback mCallBack;

    // timeout 、ua 可以进行全局设置，不需要每次请求设置
    private static int mTimeOut;
    private static String mUserAgent;

    public AbsRequestBuilder(RequestManager requestManager) {
        mRequestManager = requestManager;
    }

    public AbsRequestBuilder url(@NonNull String url) {
        mUrl = url;
        return this;
    }

    public AbsRequestBuilder params(Map<String, String> params) {
        mParams = params;
        return this;
    }

    public AbsRequestBuilder headers(Map<String, String> params) {
        mHeaders = params;
        return this;
    }

    public AbsRequestBuilder tag(Object tag) {
        mTag = tag;
        return this;
    }

    public void execute(Callback callback) {
        mCallBack = callback;
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
