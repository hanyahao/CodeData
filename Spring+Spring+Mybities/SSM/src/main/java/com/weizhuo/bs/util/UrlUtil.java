package com.weizhuo.bs.util;

import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlUtil {

    public static Map<String, Object> openUrl(String url, List<NameValuePair> param, String method) {
        if (StringUtils.isNotBlank(url)) {

            URI uri = null;
            try{
                if (param != null) {
                    uri = new URIBuilder(url).setParameters(param).build();
                }
                else {
                    uri = new URIBuilder(url).build();
                }
            }
            catch (URISyntaxException ex) {
                ex.printStackTrace();
            }

            if (uri != null) {
                HttpClient httpClient = HttpClients.createDefault();

                 HttpRequestBase request;

                if (StringUtils.equalsIgnoreCase("GET", method)) {
                    request = new HttpGet(uri);
                } else {
                    request = new HttpPost(uri);
                }

                try {
                    HttpResponse response = httpClient.execute(request);

                    if (response != null) {
                        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                        System.out.println(result);
                        return new com.google.gson.Gson().fromJson(result, new TypeToken<HashMap<String, Object>>() {}.getType());
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean isValidUrl(String url) {
        //return Constants.URL_VALIDATION_REG.matcher(url).matches();
        return true;
    }
    
    public static void main(String args[]){
    	System.out.println(isValidUrl("https://127.0.0.1:9000/api20/SenderLogin"));
    	System.out.println(isValidUrl("http://192.168.1.87:9000/api20/SenderRegister"));
    }
    
}
