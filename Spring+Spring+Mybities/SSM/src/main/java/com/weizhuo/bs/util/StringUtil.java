package com.weizhuo.bs.util;

import com.weizhuo.bs.core.common.Constants;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {
	
    public static String RemoveIllegalCharacter(String str) {
        if (StringUtils.isNotBlank(str)) {
            str = str.replace("'","").replace("\"","").replace("`","").replace("!","").replace("@","")
                .replace("#","").replace("$","").replace("%","").replace("^","").replace("&","")
                .replace("*","").replace("\\","").replace("|","").replace("<","").replace(">","")
                .replace(",","").replace(".","").replace("?","");
        }

        return str;
    }


	public static HashMap<String, String> getMap(String str) {
		HashMap<String, String> strmap = new HashMap<String, String>();
		String[] st = str.split(">");
		for (String string : st) {
			String[] s = string.substring(1).split("=");
			if (s.length == 1) {
				strmap.put(s[0], null);
			} else if (s.length == 2) {
				strmap.put(s[0], s[1]);
			}
		}
		return strmap;
	}

	public static byte[] interceptByte(byte[] b, int start, int end) {
		byte[] b2 = new byte[end - start];
		for (int i = 0; i < end - start; i++) {
			b2[i] = b[i + start];
		}
		return b2;
	}
	
	public static int bytesToInt(byte[] intByte) {
		int fromByte = 0;
		for (int i = 0; i < 4; i++) {
			int n = (intByte[i] < 0 ? (int) intByte[i] + 256 : (int) intByte[i]) << (8 * i);
			fromByte += n;
		}
		return fromByte;
	}

	public static byte[] interceptByte(byte[] b, int start) {
		byte[] b2 = new byte[b.length - start];
		for (int i = 0; i < b2.length; i++) {
			b2[i] = b[i + start];
		}
		return b2;
	}

	public static String hexToStr(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		String returnValue = sb.toString();
		sb.setLength(0);
		sb = null;
		b = null;
		return returnValue;

	}

	public static String hexToString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		String returnValue = sb.toString();
		sb.setLength(0);
		sb = null;
		b = null;
		// return returnValue;
		return addSpaceString(returnValue);

	}

	public static String addSpaceString(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			if ((i + 1) % 2 == 0) {
				sb.append(" ");
			}
		}
		return sb.toString();

	}

	/**
	 * 转换十六进制编码为字符串 ok
	 * 
	 * @param s
	 * @return
	 */
	public static String toStringHex(String s) {
		if ("0x".equals(s.substring(0, 2))) {
			s = s.substring(2);
		}
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "UTF-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" –> byte[]{0x2B, 0×44, 0xEF,
	 * 0xD9}
	 * 
	 *            String
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String str) {
		str = str.replaceAll(" ", "");
		int length = str.length() / 2;
		byte[] ret = new byte[length];
		byte[] tmp = str.getBytes();
		for (int i = 0; i < length; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		str = null;
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"–> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	private static byte uniteBytes(byte src0, byte src1) {
		byte ret = 0;
		String value = null;
		try {

			value = new String(new byte[] { src0 });
			byte _b0 = Byte.decode("0x" + value).byteValue();
			_b0 = (byte) (_b0 << 4);
			value = new String(new byte[] { src1 });
			byte _b1 = Byte.decode("0x" + value).byteValue();
			ret = (byte) (_b0 ^ _b1);
			value = null;
		} catch (NumberFormatException e) {
			System.out.println(value);
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 根据指定格式转换成日期字符串
	 * */
	public static String formatToString(Date date, String format) {
		if(date==null){
			return "";
		}
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	public static String getLogDate() {
		return formatToString(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	
	public static short getShort(byte[] b, int index) {
		return (short) (((b[index] << 8) | b[index + 1] & 0xff));
	}
	
	/**
	 * 转义get方式提交的中文字符
	 * @param str
	 * @return
	 */
	public static String fromGetRequest(String str){
		String result="";
		try {
			if(str==null || str.equals(""))return "";
			result = new String(str.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	} 
	
	public static String getString(String str){
		return str==null?"":str;
	}
	
	/**
	 * 转义特定的html标签
	 * @param str
	 * @return
	 */
	public static String htmlLableRemove(String str){
		if(StringUtils.isBlank(str)){
			return str;
		} else {
			str = StringUtils.replace(str, "<!--", "&lt;!--");
			str = StringUtils.replace(str, "-->", "--&gt;");
			str = StringUtils.replace(str, "<script>", "&lt;script&gt;");
			str = StringUtils.replace(str, "</script>", "&lt;/script&gt;");
			return str;
		}
	}
	
	/**
	 * 转义特定的字符
	 * @param str
	 * @return
	 */
	public static String htmlEscape(String str){
		if(StringUtils.isBlank(str)){
			return str;
		} else {
		    //str = StringUtils.replace(str, " ", "&nbsp;");// 替换空格
			str = StringUtils.replace(str, "\t", "&nbsp;&nbsp;");// 替换跳格
			str = StringUtils.replace(str, "\\", "&quot;");
			str = StringUtils.replace(str, "<", "&lt;");
			str = StringUtils.replace(str, ">", "&gt;");
			str = StringUtils.replace(str, ";", "&amp;");
			str = StringUtils.replace(str, "insert ", "insert&nbsp;");
			str = StringUtils.replace(str, "INSERT ", "insert&nbsp;");
			str = StringUtils.replace(str, "UPDATE ", "&nbsp;");
			str = StringUtils.replace(str, "DELETE ", "&nbsp;");
//			str = StringUtils.replace(str, "&", "&amp;");
			return str;
		}
	}
	
	public static String sqlEscape(String str){
		str = sqlReplace(str, "insert");
		str = sqlReplace(str, "update");
		str = sqlReplace(str, "delete");
		str = sqlReplace(str, "drop");
		str = sqlReplace(str, "alter");
		
		str = sqlReplace(str, "`insert`");
		str = sqlReplace(str, "`update`");
		str = sqlReplace(str, "`delete`");
		str = sqlReplace(str, "`drop`");
		str = sqlReplace(str, "`alter`");

		str = sqlReplace(str, "\"insert\"");
		str = sqlReplace(str, "\"update\"");
		str = sqlReplace(str, "\"delete\"");
		str = sqlReplace(str, "\"drop\"");
		str = sqlReplace(str, "\"alter\"");

		return str;
	}
	
	public static String sqlReplace(String str, String keyword){
		String wordReg = "(?i)" + keyword+" ";   
		StringBuffer regSb = new StringBuffer();
		Matcher matcher = Pattern.compile(wordReg).matcher(str);
		while(matcher.find())  {
		    matcher.appendReplacement(regSb, matcher.group().replace(" ", "&nbsp;")); 
		}
		matcher.appendTail(regSb);
		str = regSb.toString();

		return str;
	}
	
	public static String escape(String str){
		if(StringUtils.isBlank(str)){
			return str;
		} else {
			str = htmlEscape(str);
			str = sqlEscape(str);
//			str = StringEscapeUtils.escapeHtml(str);
//			str = StringEscapeUtils.escapeJavaScript(str);
//			str = StringEscapeUtils.escapeSql(str);

			return str;
		}
	}
	
	/**
	 * 获取异常堆栈信息
	 * @param e
	 * @return
	 */
	public static String getExceptionStackTrace(Exception e){
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer,true));
		return writer.toString();
	}
	
	/**
	 * 过滤特殊字符
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String stringFilter(String str) throws PatternSyntaxException {
		Pattern p = Constants.CHARFILTER_PATTERN;
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	} 
	
	/**
	 * 随机生成n位大小写+数字的字符串
	 * @param length
	 * @return
	 */
	public static String randomString(int length){
		Random r = new Random();
		int i = 0;
		String str = "";
		String s = null;
		while (i < length) {
			switch (r.nextInt(63)) {
				case (0): s = "0"; break;
				case (1): s = "1"; break;
				case (2): s = "2"; break;
				case (3): s = "3"; break;
				case (4): s = "4"; break;
				case (5): s = "5"; break;
				case (6): s = "6"; break;
				case (7): s = "7"; break;
				case (8): s = "8"; break;
				case (9): s = "9"; break;
				case (10): s = "a"; break;
				case (11): s = "b"; break;
				case (12): s = "c"; break;
				case (13): s = "d"; break;
				case (14): s = "e"; break;
				case (15): s = "f"; break;
				case (16): s = "g"; break;
				case (17): s = "h"; break;
				case (18): s = "i"; break;
				case (19): s = "j"; break;
				case (20): s = "k"; break;
				case (21): s = "l"; break;
				case (22): s = "m"; break;
				case (23): s = "n"; break;
				case (24): s = "o"; break;
				case (25): s = "p"; break;
				case (26): s = "q"; break;
				case (27): s = "r"; break;
				case (28): s = "s"; break;
				case (29): s = "t"; break;
				case (30): s = "u"; break;
				case (31): s = "v"; break;
				case (32): s = "w"; break;
				case (33): s = "l"; break;
				case (34): s = "x"; break;
				case (35): s = "y"; break;
				case (36): s = "z"; break;
				case (37): s = "A"; break;
				case (38): s = "B"; break;
				case (39): s = "C"; break;
				case (40): s = "D"; break;
				case (41): s = "E"; break;
				case (42): s = "F"; break;
				case (43): s = "G"; break;
				case (44): s = "H"; break;
				case (45): s = "I"; break;
				case (46): s = "L"; break;
				case (47): s = "J"; break;
				case (48): s = "K"; break;
				case (49): s = "M"; break;
				case (50): s = "N"; break;
				case (51): s = "O"; break;
				case (52): s = "P"; break;
				case (53): s = "Q"; break;
				case (54): s = "R"; break;
				case (55): s = "S"; break;
				case (56): s = "T"; break;
				case (57): s = "U"; break;
				case (58): s = "V"; break;
				case (59): s = "W"; break;
				case (60): s = "X"; break;
				case (61): s = "Y"; break;
				case (62): s = "Z"; break;
			}
			i++;
			str = s + str;
		}
		return str;
	}
	
	
	public static String randomNumber(int length){
		Random r = new Random();
		int i = 0;
		String str = "";
		String s = null;
		while (i < length) {
			switch (r.nextInt(10)) {
				case (0): s = "0"; break;
				case (1): s = "1"; break;
				case (2): s = "2"; break;
				case (3): s = "3"; break;
				case (4): s = "4"; break;
				case (5): s = "5"; break;
				case (6): s = "6"; break;
				case (7): s = "7"; break;
				case (8): s = "8"; break;
				case (9): s = "9"; break;
			}
			i++;
			str = s + str;
		}
		return str;
	}
	
	public static String keyWordsFormate(String str){
		return str.replace(" ", ",").replace(";", ",").replace("|", ",");
	}	
	/**==============================加密算法=====================================**/
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String getMd5Str(String str){
		if(str == null) return "";
		byte[] pb = null;
		try {
			pb = str.getBytes("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DigestUtils.md5DigestAsHex(pb);
	}
	
 
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
    public static String md5(String str) {   
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
        // false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true表示：生成24位的Base64版   
        md5.setEncodeHashAsBase64(false);
        String result = md5.encodePassword(str, null);
//        System.out.println("MD5: " + result + " len=" + result.length());   
        return result;   
    }   
    
    /**
     * 
     * @param str
     * @param key
     * @return
     */
    public static String md5_SystemWideSaltSource (String str, String key) {   
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
        md5.setEncodeHashAsBase64(false);   
             
        // 使用动态加密盐的只需要将第二个参数换成
        String result = md5.encodePassword(str, key);   
//        System.out.println("MD5 SystemWideSaltSource: " + result + " len=" + result.length());   
        return result;
    }
    
    /**
     * SHA-1算法
     * @param str
     * @return
     */
    public static String sha_1(String str) {   
    	return sha(str, 1);
    } 
    
    /**
     * SHA-256算法
     * @param str
     * @return
     */
    public static String sha_256(String str) {   
        return sha(str, 256);
    }   
         
    /**
     * SHA-384算法
     * @param str
     * @return
     */
    public static String sha_384(String str) {   
        return sha(str, 384);
    } 
    
    /**
     * SHA-512算法
     * @param str
     * @return
     */
    public static String sha_512(String str) {   
        return sha(str, 512);
    } 
    
    /**
     * SHA-x算法
     * @param str
     * @param strength EX: 1, 256, 384, 512
     * @return
     */
    public static String sha(String str, int strength) {   
        ShaPasswordEncoder sha = new ShaPasswordEncoder(strength);   
        sha.setEncodeHashAsBase64(false);   
        String result = sha.encodePassword(str, null);
//        System.out.println("哈希算法 "+strength+": " + result + " len=" + result.length());   
        return result;
        
    }   
    
    
	public static final String ALGORITHM = "SHA-256";
	public static String SHA256Encrypt(String orignal) { 
        MessageDigest md = null;
        try { 
            md = MessageDigest.getInstance(ALGORITHM); 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); 
        } 
        if (null != md) { 
            byte[] origBytes = orignal.getBytes(); 
            md.update(origBytes); 
            byte[] digestRes = md.digest(); 
            String digestStr = getDigestStr(digestRes); 
            return digestStr; 
        }

        return null; 
    }
    private static String getDigestStr(byte[] digestRes) { 
        String tempStr = null; 
        StringBuilder stb = new StringBuilder(); 
//        System.out.println("====="+digestRes.length);
        for (int i = 0; i < digestRes.length; i++) { 
//             System.out.println("and by bit: " + (digestRes[i] & 0xff)); 
//             System.out.println("====no and: " + digestRes[i]); 
            // 这里按位与是为了把字节转整时候取其正确的整数，java中一个int是4个字节 
            // 如果digestRes[i]最高位为1，则转为int时，int的前三个字节都被1填充了 
            tempStr = Integer.toHexString(digestRes[i] & 0xff); 
//            System.out.println("=========: " + tempStr); 
//            System.out.println("---------------------------------------------"); 
            if (tempStr.length() == 1) { 
                stb.append("0"); 
            } 
            stb.append(tempStr);

        } 
        return stb.toString(); 
    }
	
    /**============================加密算法 end=================================================*/
    
	public static void test(String string) {
		org.apache.commons.lang.StringEscapeUtils.escapeHtml(string); 
		StringEscapeUtils.escapeJavaScript(string); 
		StringEscapeUtils.escapeSql(string);
	}
	public static void main(String[] args) {
//		System.out.println(Integer.parseInt("A"));
		
//		System.out.println(getMd5Str("123")); // 使用简单的MD5加密方式   
//		
//		System.out.println(md5("123")); // 使用简单的MD5加密方式    
//        
//		System.out.println(md5_SystemWideSaltSource("123", "")); // 使用MD5再加全局加密盐加密的方式加密    
//             
//		System.out.println(sha_1("123")); // 使用SHA-1的哈希算法(SHA)加密   
//        
//		System.out.println(sha_256("123")); // 使用256的哈希算法(SHA)加密  
//        
//		System.out.println(sha_512("123")); // 使用512的哈希算法(SHA)加密  
//		
//		System.out.println(SHA256Encrypt("123"));
		
//		byte a = -1;
//		System.out.println(a & 0xff);

//		System.out.println(sqlEscape("abc Insert  aaa"));
//		System.out.println(sqlEscape("abc `InsErt`  aaa"));
		
		System.out.println(StringUtil.randomString(32));
	}
}
