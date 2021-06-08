package com.xxx.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ServletGetJSONUtils {
    public static JSONObject getJSONObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        Map params = JSONObject.parseObject(responseStrBuilder.toString(), Map.class);
        JSONObject json = (JSONObject) JSONObject.toJSON(params);
        return json;
    }
}
