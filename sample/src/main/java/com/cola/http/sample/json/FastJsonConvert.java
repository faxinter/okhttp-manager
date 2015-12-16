package com.cola.http.sample.json;

import com.alibaba.fastjson.JSON;
import com.cola.http.json.JsonConvert;

import java.util.List;

/**
 * @ Author Faxue,Wan
 * @ Date 15/12/7 下午11:03
 * @ Description // Please Add Annotation
 */
public class FastJsonConvert implements JsonConvert {

    @Override
    public <T> T convert(String jsonData, Class T) {
        return (T) JSON.parseObject(jsonData, T);
    }

    @Override
    public <T> List<T> convertList(String jsonData, Class T) {
        return (List<T>) JSON.parseArray(jsonData, T);
    }
}
