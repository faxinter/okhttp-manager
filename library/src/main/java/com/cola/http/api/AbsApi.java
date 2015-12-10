package com.cola.http.api;

import com.cola.http.RequestBuilder;
import com.cola.http.json.JsonConvert;

/**
 * @ Author SpringWater
 * @ Date 15/12/8 上午10:35
 * @ Description // Please Add Annotation
 */
public abstract class AbsApi {

//    protected RequestBuilder mRequestBuilder;
    protected JsonConvert mJsonConvert;

    public AbsApi(){
    }

    public abstract void getRequest(RequestBuilder requestBuilder);

    public abstract void postRequest(RequestBuilder requestBuilder);

    public void setJsonConvert(JsonConvert convert) {
        mJsonConvert = convert;
    }
}
