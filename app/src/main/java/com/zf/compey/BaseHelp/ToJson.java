package com.zf.compey.BaseHelp;

import com.google.gson.Gson;

import java.util.Map;

/**
 * (Map to Jsonstring )Created by ${Ethan_Zeng} on 2017/11/22.
 */

public class ToJson {
    public ToJson() {

    }

    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }
}
