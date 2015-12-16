package com.cola.http.api.okhttp;

import com.cola.http.api.AbsApi;
import com.cola.http.builder.GetRequestBuilder;
import com.cola.http.builder.PostRequestBuilder;
import com.cola.http.callback.impl.JsonListResultCallback;
import com.cola.http.callback.impl.JsonResultCallback;
import com.cola.http.callback.impl.StringResultCallback;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @ Author SpringWater
 * @ Date 15/12/8 上午10:38
 * @ Description // Please Add Annotation
 */
public class OkHttpApiImpl extends AbsApi {

    private OkHttpClient mOkHttpClient;
    OkHttpApiImpl mApiImpl;

    public OkHttpApiImpl() {
        super();
        mOkHttpClient = new OkHttpClient();
        mApiImpl = this;
    }

    @Override
    public void httpGetRequest(final GetRequestBuilder requestBuilder) {
        // 构建 OkHttp 所需要的 Request
        Request request = new Request.Builder()
                .url(requestBuilder.getUrl())
                .headers(Headers.of(requestBuilder.getHeaders()))
                .build();

        final com.cola.http.callback.Callback callback = requestBuilder.getCallBack();
        // 网络请求
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (null != callback) {
                    mOkHttp.sendFailMessage(e, callback);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mApiImpl.onResponseResult(callback, response);
            }
        });
    }

    @Override
    public void httpPostRequest(PostRequestBuilder requestBuilder) {

        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        Map<String, String> params = requestBuilder.getParams();

        if (null != params && !params.isEmpty()) {
            for (String key : params.keySet()) {
                formEncodingBuilder.add(key, params.get(key));
            }
        }

        Request request = new Request.Builder()
                .url(requestBuilder.getUrl())
                .post(formEncodingBuilder.build())
                .build();
        final com.cola.http.callback.Callback callback = requestBuilder.getCallBack();
        // 网络请求
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (null != callback) {
                    mOkHttp.sendFailMessage(e, callback);
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mApiImpl.onResponseResult(callback, response);
            }
        });
    }

    private void onResponseResult(com.cola.http.callback.Callback callback, Response response) throws IOException {
        String result = response.body().string();
        if (response.isSuccessful()) {
            if (callback instanceof StringResultCallback) {
                mOkHttp.sendMessage(callback, result);
            } else if (callback instanceof JsonResultCallback) {
                if (null == mJsonConvert) {
                    throw new IllegalStateException(" You must call OkHttp.jsonConvert() !");
                }
                // Json -> Object
                Type genType = callback.getClass().getGenericSuperclass();
                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                Object object = mJsonConvert.convert(result, (Class) params[0]);

                mOkHttp.sendMessage(callback, object);
            } else if (callback instanceof JsonListResultCallback) {
                if (null == mJsonConvert) {
                    throw new IllegalStateException(" You must call OkHttp.jsonConvert() !");
                }
                // Json -> Object
                Type genType = callback.getClass().getGenericSuperclass();
                ParameterizedType parameterized = (ParameterizedType) genType;
                Type[] params = parameterized.getActualTypeArguments();
                ParameterizedType pType = (ParameterizedType) params[0];

                Type[] subParams = pType.getActualTypeArguments();
                Object object = mJsonConvert.convertList(result, (Class) subParams[0]);

                mOkHttp.sendMessage(callback, object);
            }
        } else {
            mOkHttp.sendFailMessage(new RuntimeException(result), callback);
        }
    }

}
