package com.weizhuo.bs.core.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weizhuo.bs.core.common.Message;

public class IntegerCheck extends Rule {
	private int min = Integer.MIN_VALUE;
	private int max = Integer.MAX_VALUE;
	
	public IntegerCheck(){
	}
	
	public IntegerCheck(int min){
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
				int realValue = Integer.parseInt(this.value);
				//判断取值范围
				if(realValue<min || realValue>max) {
					message = Message.get("rule.Integer");
					return false;
				}else {
					return true;
				}
			} catch (Exception e) {
				message = Message.get("rule.Integer");
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		String re = "^[+-]{0,1}([0-9]|([1-9][0-9]*))$";
//		String re = "^[a-zA-Z0-9]+_\\w+$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher("0");
		System.out.println(matcher.matches());
	}
}
