package com.cola.http.json;

/**
 * @ Author SpringWater
 * @ Date 15/12/7 下午11:01
 * @ Description // Please Add Annotation
 */
public interface JsonConvert {
    public <T> T execute(String jsonData, Class T);
}
