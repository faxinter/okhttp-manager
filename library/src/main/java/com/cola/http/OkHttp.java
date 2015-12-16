package com.cola.http;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;

import com.cola.http.builder.AbsRequestBuilder;
import com.cola.http.builder.GetRequestBuilder;
import com.cola.http.builder.PostRequestBuilder;
import com.cola.http.builder.RequestType;
import com.cola.http.callback.Callback;
import com.cola.http.json.JsonConvert;

/**
 * @ Author SpringWater
 * @ Date 15/12/7 下午11:05
 * @ Description // Please Add Annotation
 */
public class OkHttp {
    private static OkHttp mInstance;
    private static JsonConvert mJsonConvert;

    private Handler mHandler;

    private OkHttp() {
        mHandler = new Handler(Looper.myLooper());
    }

    public static OkHttp getInstance() {
        if (mInstance == null) {
            synchronized (OkHttp.class) {
                if (mInstance == null) {
                    mInstance = new OkHttp();
                }
            }
        }
        return mInstance;
    }

    public synchronized void init() {

    }

    // TODO with(*) 关联相关生命周期
    public static RequestManager with(Context context) {
        return null;
    }

    public static RequestManager with(Activity activity) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static RequestManager with(android.app.Fragment fragment) {
        return null;
    }

    public static RequestManager with(Fragment fragment) {
        return null;
    }

    public static AbsRequestBuilder get() {
        getInstance();
        return getHttpRequest(RequestType.GET);
    }

    public static AbsRequestBuilder post() {
        getInstance();
        return getHttpRequest(RequestType.POST);
    }

    private static AbsRequestBuilder getHttpRequest(RequestType type) {
        switch (type) {
            case GET:
                return new GetRequestBuilder(new RequestManager());
            case POST:
                return new PostRequestBuilder(new RequestManager());
        }
        return null;
    }

    // 全局 -----
    public void setTimeout() {

    }

    public void setHttpCache(long cacheTime) {

    }

    public void setUserAgent(String ua) {

    }

    public void jsonConvert(JsonConvert jsonConvert) {
        mJsonConvert = jsonConvert;
    }
    // 全局 -----

    public JsonConvert getJsonConvert() {
        return mJsonConvert;
    }

    public void sendMessage(final Callback callback, final Object o) {
        if (null != callback) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(o);
                }
            });
        }
    }

    public void sendFailMessage(final Exception e, final Callback callback) {
        if (null != callback) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onFailure(-1, e);
                }
            });
        }
    }

//    private void handleMessage(Message message) {
//
//    }
//
//    private Message obtainMessage(int messageId,Callback callback, Object messageData) {
//        return Message.obtain(mHandler, messageId,  messageData);
//    }
//
//    private static class ResponderHandler extends Handler {
//        private final OkHttp mHttpManager;
//
//        ResponderHandler(OkHttp manager, Looper looper) {
//            super(looper);
//            this.mHttpManager = manager;
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            mHttpManager.handleMessage(msg);
//        }
//    }
}
