package com.weizhuo.bs.core.rules;

import com.weizhuo.bs.core.common.Message;

public class Required extends Rule{
	
	public Required(){
		
	}
	
	public boolean valid(){
		if(this.getValue()==null || this.getValue().equals("")){
			this.setMessage(Message.get("rule.required"));
			return false;
		}else {
			return true;
		}
	}
}
