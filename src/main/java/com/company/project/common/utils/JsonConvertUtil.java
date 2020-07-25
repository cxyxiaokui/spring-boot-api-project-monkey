package com.company.project.common.utils;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Json和Object的互相转换
 * @Author zhuoqianmingyue
 * @Date 2020/7/11
 **/
public class JsonConvertUtil {
    private JsonConvertUtil() {}

    /**
     * JSON 转 Object
     */
    public static <T> T jsonToObject(String pojo,Class<T> clazz) {
        Gson gson = initGson(null, false);
        return gson.fromJson(pojo, clazz);
    }
    /**
     * JSON 转 Object
     */
    public static <T> T jsonToObject(String pojo, String dateFormat ,Class<T> clazz,boolean isHasNullProperty) {
        Gson gson = initGson(dateFormat, isHasNullProperty);
        return gson.fromJson(pojo, clazz);
    }
    /**
     * JSON 转 Object
     */
    public static <T> String toJson(T t) {
        Gson gson = initGson(null, false);
        return gson.toJson(t);
    }
    /**
     * JSON 转 Object
     */
    public static <T> String toJson(T t, String dateFormat , boolean isHasNullProperty) {
        Gson gson = initGson(dateFormat, isHasNullProperty);
        return gson.toJson(t);
    }


    public static <T> List<T> jsonToList(String jsonString) {
        Gson gson = initGson(null, false);
        return gson.fromJson(jsonString, new TypeToken<List<T>>() {}.getType());
    }

    private static Gson initGson(String dateFormat, boolean isHasNullProperty) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        if (StrUtil.isNotEmpty(dateFormat)) {
            gsonBuilder.setDateFormat(dateFormat);
        }

        if (isHasNullProperty) {
            gsonBuilder.serializeNulls();
        }
        return gsonBuilder.create();
    }

}
