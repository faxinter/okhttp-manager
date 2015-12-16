package com.cola.http.builder;

import com.cola.http.RequestManager;
import com.cola.http.callback.Callback;

/**
 * @ Author SpringWater
 * @ Date 15/12/15 下午7:39
 * @ Description // Please Add Annotation
 */
public class PostRequestBuilder extends AbsRequestBuilder {

    public PostRequestBuilder(RequestManager requestManager) {
        super(requestManager);
    }

    @Override
    public void execute(Callback callback) {
        super.execute(callback);
        mRequestManager.doPostRequest(this);
    }
}
