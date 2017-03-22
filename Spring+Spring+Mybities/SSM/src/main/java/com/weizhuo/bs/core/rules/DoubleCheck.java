package com.weizhuo.bs.core.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weizhuo.bs.core.common.Message;

public class DoubleCheck extends Rule {
	private int decimalDigitsMin=1;//小数位数最少值
	private int decimalDigitsMax=6;//小数位数最大值
	
	private String reg = "^((0)|([1-9][0-9]*))\\.\\d%s$";
	private Pattern pattern;
	
	
	public DoubleCheck(){
		reg = String.format(reg, "{"+decimalDigitsMin+","+decimalDigitsMax+"}");
		this.pattern = Pattern.compile(reg);
	}
	
	public DoubleCheck(int decimalDigitsMax){
		this.decimalDigitsMax = decimalDigitsMax;
		
		reg = String.format(reg, "{"+decimalDigitsMax+"}");
		this.pattern = Pattern.compile(reg);
	}
	
	public DoubleCheck(int decimalDigitsMin, int decimalDigitsMax){
		this.decimalDigitsMin = decimalDigitsMin;
		this.decimalDigitsMax = decimalDigitsMax;
		
		reg = String.format(reg, "{"+decimalDigitsMin+","+decimalDigitsMax+"}");
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
			double realValue = Double.parseDouble(this.value);
			return true;
		} catch (Exception e) {
			message = Message.get("rule.double");
			return false;
		}
	}
	public static void main(String[] args) {
//		String reg = "^\\d{2}$";
//		Pattern pattern = Pattern.compile(reg);
//		Matcher matcher = pattern.matcher("91");
//		System.out.println(matcher.matches());
		

		//String reg = "^((0)|([1-9][0-9])*)\\.\\d{1,6}$";
		String reg = "^((0)|([1-9][0-9]*))\\.\\d%s$";
		reg = String.format(reg, "{"+6+"}");
		System.out.println(reg);
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher("0.0");
		System.out.println(matcher.matches());
	}
}
