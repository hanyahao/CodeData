package com.weizhuo.bs.core.rules;

import com.weizhuo.bs.core.common.Message;

public class BooleanCheck extends Rule {
	
	@Override
	public boolean valid() throws Exception {
        if (this.getValue() == null) {
            return true;
        }

		if (this.getValue() != null && 
				(this.getValue().equalsIgnoreCase(Boolean.TRUE.toString()) 
						|| this.getValue().equalsIgnoreCase(Boolean.FALSE.toString()))){
			return true;
		}
		else {
			this.setMessage(Message.get("rule.booleanValue"));
			return false;			
		}
	
	}
}
