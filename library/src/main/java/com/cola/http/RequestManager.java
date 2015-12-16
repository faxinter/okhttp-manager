package com.cola.http;

import com.cola.http.api.AbsApi;
import com.cola.http.api.okhttp.OkHttpApiImpl;
import com.cola.http.builder.GetRequestBuilder;
import com.cola.http.builder.PostRequestBuilder;
import com.cola.http.builder.RequestBuilder;

/**
 * @ Author SpringWater
 * @ Date 2015/12/10 17:05
 * @ Description // Please Add Annotation
 */
public class RequestManager {
    private AbsApi mApi;

    public RequestManager() {
        mApi = new OkHttpApiImpl();
    }

    public void doRequest(RequestBuilder requestBuilder) {
        verifyApi();

        if (requestBuilder instanceof GetRequestBuilder) {

            mApi.httpGetRequest(requestBuilder);

        } else if (requestBuilder instanceof PostRequestBuilder) {

            mApi.httpPostRequest(requestBuilder);
        }
    }

    private void verifyApi() {
        if (null == mApi) {
            throw new IllegalArgumentException("you must invoke setOKHttpEnabled()");
        }
    }

    public void doDownLoad() {

    }
}
