package com.cola.http;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.cola.http.callback.Callback;
import com.cola.http.json.JsonConvert;

/**
 * @ Author SpringWater
 * @ Date 15/12/7 下午11:05
 * @ Description // Please Add Annotation
 */
public class OkHttp {

    private static final int MESSAGE_SUCCESS = 0;
    private static final int MESSAGE_FAILURE = 1;

    private static OkHttp mInstance;

    private JsonConvert mJsonConvert;

    private ResponderHandler mHandler;

    private OkHttp() {
        mHandler = new ResponderHandler(this, Looper.myLooper());
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

    public static RequestBuilder get() {
        return new RequestBuilder(getInstance());
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

    public RequestBuilder getHttpRequestBuilder() {
        return new RequestBuilder(this);
    }

    public OkHttp jsonConvert() {
        return getInstance();
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

    }

    private void handleMessage(Message message) {

    }

    private Message obtainMessage(int messageId, Object messageData) {
        return Message.obtain(mHandler, messageId, messageData);
    }

    private static class ResponderHandler extends Handler {
        private final OkHttp mHttpManager;

        ResponderHandler(OkHttp manager, Looper looper) {
            super(looper);
            this.mHttpManager = manager;
        }

        @Override
        public void handleMessage(Message msg) {
            mHttpManager.handleMessage(msg);
        }
    }
}
