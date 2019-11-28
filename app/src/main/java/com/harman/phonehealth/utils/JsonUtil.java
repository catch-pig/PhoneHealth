package com.harman.phonehealth.utils;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class JsonUtil {

    public static String mapToJson(Map<String, Double> map) {
        if (map != null) {
            return JSON.toJSONString(map);
        }
        return null;
    }

    public static Map<String, Double> jsonToMap(String json) {
        if (json != null) {
            return JSON.parseObject(json, new com.alibaba.fastjson.TypeReference<Map<String, Double>>() {
            });
        }
        return null;
    }
}
