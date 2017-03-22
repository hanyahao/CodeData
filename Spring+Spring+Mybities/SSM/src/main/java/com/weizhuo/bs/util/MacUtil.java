package com.weizhuo.bs.util;


/**
 * 
 */
public class MacUtil {
    public static String convertMacToTwifiFormat(String org_mac) {
        String formatted_mac = org_mac;

        if (formatted_mac!=null && !"".equals(formatted_mac)) {
            formatted_mac = formatted_mac.toUpperCase().replace(":", "").replace("-", "").replace(".", "").replace("_", "").replace(" ","");

        }

        return formatted_mac;
    }
}
