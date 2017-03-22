package com.weizhuo.bs.core.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weizhuo.bs.core.common.Message;

public class Regex extends Rule{
	private String reg;
	private Pattern pattern;
	public Regex(String reg){
		this.reg = reg;
		this.pattern = Pattern.compile(reg);
	}
	public Regex(Pattern pattern){
		this.pattern = pattern;
	}

	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}

	public Pattern getPattern() {
		return pattern;
	}
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
	
	
	@Override
	public boolean valid() throws Exception{
		if(value!=null && !"".equals(value)){
			Matcher matcher = this.pattern.matcher(value);
			if(!matcher.matches()){
				message = Message.get("rule.regex");
				return false;
			}else {
				return true;
			}
		}else{
			return true;
		}
	}
	
	public static void main(String[] args) {
		String re = "^[a-zA-Z0-9]+_\\w+_\\d{8}$";
//		String re = "^[a-zA-Z0-9]+_\\w+$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher("sanku_sanku_001_84886977");
		System.out.println(matcher.matches());
	}
}
