package com.weizhuo.bs.core.rules;

import com.weizhuo.bs.core.common.Message;

public class LongCheck extends Rule {
	private long min = Long.MIN_VALUE;
	private long max = Long.MAX_VALUE;
	
	public LongCheck(){}
	
	public LongCheck(long min){
		this.min = min;
	}
	
	@Override
	public boolean valid() throws Exception {
		if(this.value==null || "".equals(this.value)){
			return true;
		} else {
			/*String reg = "^[+-]{0,1}([0-9]|([1-9][0-9]*))$";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(value);*/
			
			try {
				long realValue = Long.parseLong(this.value);
				
				//判断取值范围
				if(realValue<min || realValue>max) {
					message = Message.get("rule.Long");
					return false;
				}else {
					return true;
				}
			} catch (Exception e) {
				message = Message.get("rule.Long");
				return false;
			}
		}
	}
}
