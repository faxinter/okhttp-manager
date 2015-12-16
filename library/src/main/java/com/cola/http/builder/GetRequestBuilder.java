package com.cola.http.builder;

import com.cola.http.RequestManager;
import com.cola.http.callback.Callback;

import java.util.Map;

/**
 * @ Author SpringWater
 * @ Date 15/12/15 下午7:39
 * @ Description // Please Add Annotation
 */
public class GetRequestBuilder extends AbsRequestBuilder {

    public GetRequestBuilder(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    public AbsRequestBuilder params(Map<String, String> params) {
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

    @Override
    public void execute(Callback callback) {
        super.execute(callback);
        mRequestManager.doGetRequest(this);
    }
}
