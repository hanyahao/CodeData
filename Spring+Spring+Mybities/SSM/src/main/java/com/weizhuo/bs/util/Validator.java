package com.weizhuo.bs.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weizhuo.bs.core.common.Message;

public class Validator{
	public static final String charset = "UTF-8";
	
	public String result = null;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void isBoolean(String value, String name) throws Exception {
		if(this.result == null){
			if(value != null){
				if (!(value.equalsIgnoreCase(Boolean.TRUE.toString()) 
						|| value.equalsIgnoreCase(Boolean.FALSE.toString()))){
					this.result = Message.get("message.booleanValue");
				}
			}
		}
	}
	
	public void array(String value, String[] checArr, String name) throws Exception{
		if(this.result == null){
			if(value != null){
				List<String> list = Arrays.asList(checArr);
				if(!(value!=null && list.contains(value))){
					this.result = Message.get("message.checkBox");
				}
			}
		}
	}
    
	public void date(String value, String name) throws Exception {
		if (this.result == null) {
			if(value != null){
				Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
				Matcher matcher = p.matcher(value);
				if(!matcher.matches()){
					this.result = Message.get("rule.dateValue");
				}
			}
		}
	}
	
	public void length(String value, int max, String name) throws Exception{
		length(value, 0, max, name);
	}
	
	public void length(String value, int min, int max, String name) throws Exception{
		if (this.result == null) {
			if(isBlank(value)){
				this.result = Message.get("message.notNull");
			} else {
				if(value.getBytes(charset).length<min 
						|| value.getBytes(charset).length>max){
					this.result = Message.get("message.length");
				}
			}
		}
	}
	
	
	public void numberLimit(String value, String min, String max, String name) throws Exception {
		if (this.result == null) {
			if(isNotBlank(value)){
				BigDecimal realValue = new BigDecimal(value);
				BigDecimal minValue = new BigDecimal(min);
				
				if(max==null || "".equals(max)){
					if(realValue.compareTo(minValue) == -1) {
						this.result = Message.get("rule.numberRange") + min;
					}
				}else {
					BigDecimal maxValue = new BigDecimal(max);
					if(realValue.compareTo(minValue)==-1 || realValue.compareTo(maxValue)==1) {
						result = Message.get("rule.numberRange") + min + "-" + max;
					}
				}
			}
		}
	}
	
	public void number(String value, String name) throws Exception {
		if(this.result==null){
			if(isNotBlank(value)){
				if (!isNumeric(value)) {
					this.result = Message.get("rule.numeric");
				}
			}
		}
	}
	
	
	public void regex(String value, String regex, String name) throws Exception{
		if(this.result==null){
			if(isNotBlank(value)){
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(value);
				if(!matcher.matches()){
					result = Message.get("rule.regex");
				}
			}
		}
	}
	
	public void required(String value, String name){
		if(value==null || "".equals(value)){
			this.result = Message.get("rule.required");
		}
	}

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumeric(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}