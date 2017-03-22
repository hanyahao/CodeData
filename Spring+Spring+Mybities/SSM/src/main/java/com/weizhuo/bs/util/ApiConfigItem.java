package com.weizhuo.bs.util;

import org.apache.commons.lang.StringUtils;

import java.util.Map;


public class ApiConfigItem {

    /**
     * 接口名称
     */
    private String groupName;

    private Map<String, Map<String, String>> interfaces;

    public ApiConfigItem(Object configItemObj) {
        Map<String, Object> configItemMap = (Map<String, Object>)configItemObj;
        this.groupName = configItemMap.get("group_name").toString();
        this.interfaces = (Map<String, Map<String, String>>)configItemMap.get("interfaces");

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Map<String, Map<String, String>> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Map<String, Map<String, String>> interfaces) {
        this.interfaces = interfaces;
    }

    public Map<String, String> getApiInfoByName(String apiName) {
        Map<String, String> result = null;
        if (StringUtils.isNotBlank(apiName) && this.interfaces.containsKey(apiName)) {
            result = this.interfaces.get(apiName);
        }

        return result;
    }

    public boolean contains(String apiName) {
        return StringUtils.isNotBlank(apiName) && this.interfaces != null && this.interfaces.containsKey(apiName);
    }
}
