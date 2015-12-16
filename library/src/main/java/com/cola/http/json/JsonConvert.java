package com.cola.http.json;

import java.util.List;

/**
 * @ Author SpringWater
 * @ Date 15/12/7 下午11:01
 * @ Description // Please Add Annotation
 */
public interface JsonConvert {
    /**
     * 转换 json -> 对象
     *
     * @param jsonData json
     * @param T        要转换为的对象
     * @param <T>      再返回给对象
     * @return 集合
     */
    public <T> T convert(String jsonData, Class T);

    /**
     * 转换 json -> 对象数组
     *
     * @param jsonData json
     * @param T        要转换为的对象
     * @param <T>      再返回给对象
     * @return 集合
     */
    public <T> List<T> convertList(String jsonData, Class T);
}
