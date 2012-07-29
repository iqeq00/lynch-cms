package com.lynch.cms.common.string;

/**
 * String常用工具类
 * 
 * @author Lynch Yao
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class StringUtil {
	
	/**
	 * int转换为String
	 * 
	 * @param i
	 * @return
	 */
	public static String intToString(int i) {

		return String.valueOf(i);
	}

	/**
	 * Long转换为String
	 * 
	 * @param l
	 * @return
	 */
	public static String longToString(Long l) {
		
		return String.valueOf(l);
	}
	
	/**
	 * Double转换为String
	 * 
	 * @param d
	 * @return
	 */
	public static String doubleToString(Double d) {
		
		return String.valueOf(d);
	}
	
	/**
	 * Float转换为String
	 * 
	 * @param f
	 * @return
	 */
	public static String floatToString(Float f) {
		
		return String.valueOf(f);
	}
	
	/**
	 * 判断一个String是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isNull(String str) {
		
		if(str == null || str == "" || str.length() == 0) {		
			return true;
		}
		return false;		
	}
	
	/**
	  * 去除字符串左边多余的空格
	  * 
	  * @param value
	  * @return 
	  */
	public static String trimLeft(String str) {

		String result = str;
		if (result == null) {
			return result;
		}
		char ch[] = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}
	
	/**
	 * 去除字符串右边多余的空格
	 * 
	 * @param value
	 * @return 
	 */
	public static String trimRight(String str) {
		
		String result = str;
		if (result == null) {
			return result;
		}
		char ch[] = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 去掉字符串左边和右边的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimString(String str) {
		
		if(!isNull(str)) {	
			return str.trim();
		}	
		return str;
	}
	
	/**
	 * html标签转换成ascii码
	 * 
	 * @param str
	 * @return
	 */
	public static final String htmlToAscii(String str) {
		
		if (isNull(str)) {
			return str;
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("'", "&#39");
		str = str.replaceAll("\n", "<br>");
		return str;
	}

	/**
	 * ascii码转换成html标签
	 * 
	 * @param str
	 * @return
	 */
	public static final String asciiToHtml(String str) {
		
		if (isNull(str)) {
			return str;
		}
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&nbsp;", " ");
		str = str.replaceAll("&#39", "'");
		str = str.replaceAll("<br>", "\r\n");
		return str;
	}

}

