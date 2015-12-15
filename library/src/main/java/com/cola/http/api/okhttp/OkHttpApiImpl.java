package com.cola.http.api.okhttp;

import com.cola.http.OkHttp;
import com.cola.http.RequestBuilder;
import com.cola.http.api.AbsApi;
import com.cola.http.callback.impl.StringResultCallback;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ Author SpringWater
 * @ Date 15/12/8 上午10:38
 * @ Description // Please Add Annotation
 */
public class OkHttpApiImpl extends AbsApi {

    private OkHttpClient mOkHttpClient;
    private OkHttp mOkHttp;

    public OkHttpApiImpl() {
        super();
        mOkHttpClient = new OkHttpClient();
        mOkHttp = OkHttp.getInstance();
    }

    @Override
    public void httpGetRequest(final RequestBuilder requestBuilder) {
        // 构建 OkHttp 所需要的 Request
        Request request = new Request.Builder()
                .url(requestBuilder.getUrl())
                .build();

        final com.cola.http.callback.Callback callback = requestBuilder.getCallBack();
        // 网络请求
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (null != callback) {

                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.code() >= 400 && response.code() <= 599) {
                    try {
                        mOkHttp.sendFailMessage(new RuntimeException(response.body().string()), callback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                if (callback instanceof StringResultCallback) {
                    mOkHttp.sendMessage(callback, response.body().toString());
                } else {
                    // Json -> Object
                    Type genType = getClass().getGenericSuperclass();
                    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                    Object object = mJsonConvert.execute(response.body().string(), (Class) params[0]);
                    mOkHttp.sendMessage(callback, object);
                }
            }
        });
    }

    @Override
    public void httpPostRequest(RequestBuilder requestBuilder) {

    }
}
