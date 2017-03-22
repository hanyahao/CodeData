package com.weizhuo.bs.core.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weizhuo.bs.core.common.Message;

public class FloatCheck extends Rule{
	private String reg = "^((0)|([1-9][0-9]*))\\.[\\d]{2,6}$";
	private Pattern pattern;
	
	public FloatCheck(){
		this.pattern = Pattern.compile(reg);
	}

	public Pattern getPattern() {
		return pattern;
	}
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public boolean valid() throws Exception{
		if(value==null && "".equals(value)){
			return true;
		}
		
		try {
			float realValue = Float.parseFloat(this.value);
			return true;
		} catch (Exception e) {
			message = Message.get("rule.float");
			return false;
		}
	}
	public static void main(String[] args) {
//		String reg = "^\\d{2}$";
//		Pattern pattern = Pattern.compile(reg);
//		Matcher matcher = pattern.matcher("91");
//		System.out.println(matcher.matches());
		

		String reg = "^((0)|([1-9][0-9])*)\\.\\d{2,6}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher("91.12");
		System.out.println(matcher.matches());
	}
}
