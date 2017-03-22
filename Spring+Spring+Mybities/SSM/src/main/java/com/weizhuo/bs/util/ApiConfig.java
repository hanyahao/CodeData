package com.weizhuo.bs.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ApiConfig {
    private List<ApiConfigItem> items;

    public List<ApiConfigItem> getItems() {
        return items;
    }

    public void setItems(List<ApiConfigItem> items) {
        this.items = items;
    }

    public ApiConfig(List<Object> api_obj_list) {
        if (api_obj_list != null && api_obj_list.size() > 0) {
            this.items = new ArrayList<>();

            for (Object i : api_obj_list) {
                this.items.add(new ApiConfigItem(i));
            }
        }
    }

    public Map<String, String> getApiInfoByName(String apiName) {
        Map<String, String> result  = null;
        if (StringUtils.isNotBlank(apiName) && this.items != null && this.items.size() > 0) {
            for (ApiConfigItem item : items) {
                if (item.contains(apiName)) {
                    result = item.getApiInfoByName(apiName);
                    break;
                }
            }
        }
        return result;
    }

    public String getUrlByName(String apiName) {
        if (StringUtils.isNotBlank(apiName)) {
        	Map<String, String> apiInfo = this.getApiInfoByName(apiName);
        	if(apiInfo==null){
        		return null;
        	}
        	
            String url = apiInfo.get("url");
            
            if (UrlUtil.isValidUrl(url)) {
                return url;
            }
        }

        return null;
    }
}
