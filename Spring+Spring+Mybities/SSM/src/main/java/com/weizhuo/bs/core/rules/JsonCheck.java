package com.weizhuo.bs.core.rules;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weizhuo.bs.core.common.Message;

public class JsonCheck extends Rule{
	private Gson gson = new Gson();
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean valid() throws Exception{
		if(value!=null && !value.equals("")){
			try {
				if(value.startsWith("{")){
					gson.fromJson(value, new TypeToken<Map>(){}.getType());
					return true;
				} else if (value.startsWith("[")){
					gson.fromJson(value, new TypeToken<List>(){}.getType());
					return true;
				} else{
					message = Message.get("rule.jsonError");
					return false;
				}
			} catch (Exception e) {
				message = Message.get("rule.jsonError");
				return false;
			}
		}else{
			return true;
		}
	}
}
