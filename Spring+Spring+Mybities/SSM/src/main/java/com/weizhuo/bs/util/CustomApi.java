package com.weizhuo.bs.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomApi {
    public class ApiGroup {
        public String group_name;
        public Boolean is_group_set;
        public int interface_num;
        public Map<String, Map<String, String>> interfaces;

        public Map<String, Object> getMappedObj() {
            Map<String, Object> result = new HashMap<String, Object>();

            result.put("group_name", this.group_name);
            result.put("is_group_set", this.is_group_set);
            result.put("interface_num", this.interface_num);
            result.put("interfaces", this.interfaces);

            return result;
        }
    }

    private Gson gson = new Gson();

    private String jsonString = StringUtils.EMPTY;

    private List<ApiGroup> groups = null;

    public CustomApi(String jsonString) {
        this.jsonString = jsonString;

        ConvertJsonToObj();
    }

    public List<ApiGroup> getGroups() {
        return this.groups;
    }

    public Map<String, String> getApiInfoByName(String apiName) {
        if (StringUtils.isNotBlank(apiName) && this.groups != null) {
            for (ApiGroup group: this.groups) {
                if (group != null && group.interfaces != null && group.interfaces.containsKey(apiName.toLowerCase())) {
                    return group.interfaces.get(apiName.toLowerCase());
                }
            }
        }

        return null;
    }

    public String getApiUrlByName(String apiName) {
        Map<String, String> apiInfo = this.getApiInfoByName(apiName);

        if (apiInfo != null && apiInfo.containsKey("url")) {
            return apiInfo.get("url");
        }
        else {
            return null;
        }
    }

    public String getJson() {
        List<Map<String, Object>> mappedGroups = new ArrayList<Map<String, Object>>();

        for (ApiGroup group : this.groups) {
            mappedGroups.add(group.getMappedObj());
        }

        String apisJsonStr = gson.toJson(mappedGroups);
        System.out.println("CustomApi json String: " + apisJsonStr);
        return apisJsonStr;
    }

    @SuppressWarnings("unchecked")
	private void ConvertJsonToObj() {
        if (StringUtils.isNotBlank(this.jsonString)) {
            List<Map<String, Object>> mappedGroups = gson.fromJson(this.jsonString, new TypeToken<List<Map<String, Object>>>() {}.getType());

            if (mappedGroups != null) {
                this.groups = new ArrayList<ApiGroup>();

                for (Map<String, Object> mappedGroup : mappedGroups) {
                    ApiGroup group = new ApiGroup();
                    group.group_name = (String)mappedGroup.get("group_name");
                    group.is_group_set = (double)mappedGroup.get("is_group_set") == 1.0;
                    group.interface_num = (new Double((double)mappedGroup.get("interface_num"))).intValue();
                    group.interfaces = (Map<String, Map<String, String>>)mappedGroup.get("interfaces");

                    this.groups.add(group);
                }
            }
        }
    }

}


