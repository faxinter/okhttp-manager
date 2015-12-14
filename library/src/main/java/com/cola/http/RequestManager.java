package com.cola.http;

import com.cola.http.api.AbsApi;
import com.cola.http.api.okhttp.OkHttpApiImpl;

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

    public void doGet(RequestBuilder requestBuilder) {
        verifyApi();
        mApi.httpGetRequest(requestBuilder);
    }

    public void doPost(RequestBuilder requestBuilder) {
        verifyApi();
        mApi.httpPostRequest(requestBuilder);
    }

    private void verifyApi() {
//        if (null == mApi) {
//            throw new IllegalArgumentException("you must invoke setOKHttpEnabled()");
//        }
    }

    public void doDownLoad() {

    }
}
