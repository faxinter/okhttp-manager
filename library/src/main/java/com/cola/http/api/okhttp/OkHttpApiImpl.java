package com.cola.http.api.okhttp;

import com.cola.http.OkHttp;
import com.cola.http.RequestBuilder;
import com.cola.http.api.AbsApi;
import com.cola.http.callback.impl.StringResultCallBack;
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

    public OkHttpApiImpl() {
        super();
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public void getRequest(final RequestBuilder requestBuilder) {
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
                // Json -> Object
                if (null == callback || callback instanceof StringResultCallBack) {

                } else {
                    Type genType = getClass().getGenericSuperclass();
                    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
//                    entityClass = (Class) params[0];

                    Object object = mJsonConvert.execute(response.body().string(), (Class) params[0]);
                    OkHttp.getInstance().sendMessage(callback, object);
                }
            }
        });
    }

    @Override
    public void postRequest(RequestBuilder requestBuilder) {

    }
}
