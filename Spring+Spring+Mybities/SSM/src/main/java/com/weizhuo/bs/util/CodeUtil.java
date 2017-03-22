package com.weizhuo.bs.util;

import com.weizhuo.bs.core.common.Message;

import java.util.HashMap;
import java.util.Map;

public class CodeUtil {

    public static Map<String, String> getFailCode(String code){
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("message", Message.get(code));
        return resultMap;
    }


    public static Map<String, String> getOk(){
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("message", "success");
        resultMap.put("code", "00000");
        return resultMap;
    }


    public static Map<String, String> getFail(String code, String message) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("message", message);
        return resultMap;
    }
}
