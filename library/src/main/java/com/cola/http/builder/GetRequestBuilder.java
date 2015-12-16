package com.cola.http.builder;

import com.cola.http.RequestManager;

import java.util.Map;

/**
 * @ Author SpringWater
 * @ Date 15/12/15 下午7:39
 * @ Description // Please Add Annotation
 */
public class GetRequestBuilder extends RequestBuilder {

    public GetRequestBuilder(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(mUrl.contains("?") ? "&" : "?");

        if (null != params && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
            mUrl += sb.toString();
        }
        return this;
    }
}
