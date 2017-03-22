package com.weizhuo.bs.core.rules;

import org.apache.commons.lang.StringUtils;

import com.weizhuo.bs.core.common.Message;

public class Numeric extends Rule {
	
	public Numeric() {
		
	}
	
	@Override
	public boolean valid() throws Exception {
		if(this.getValue() == null){
			return true;
		}else{
			if (StringUtils.isNumeric(this.getValue())) {
				return true;
			} else {
				this.setMessage(Message.get("rule.numeric"));
				return false;
			}
		}
	}

}
