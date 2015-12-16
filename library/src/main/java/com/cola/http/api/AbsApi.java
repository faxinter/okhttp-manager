package com.cola.http.api;

import com.cola.http.OkHttp;
import com.cola.http.builder.GetRequestBuilder;
import com.cola.http.builder.PostRequestBuilder;
import com.cola.http.json.JsonConvert;

/**
 * @ Author SpringWater
 * @ Date 15/12/8 上午10:35
 * @ Description // Please Add Annotation
 */
public abstract class AbsApi {

    protected OkHttp mOkHttp;
    protected JsonConvert mJsonConvert;

    public AbsApi() {
        mOkHttp = OkHttp.getInstance();
        mJsonConvert = mOkHttp.getJsonConvert();
    }

    public abstract void httpGetRequest(GetRequestBuilder requestBuilder);

    public abstract void httpPostRequest(PostRequestBuilder requestBuilder);

    public void setJsonConvert(JsonConvert convert) {
        mJsonConvert = convert;
    }
}
