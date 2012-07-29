package com.lynch.cms.common.num;

import org.apache.log4j.Logger;

/**
 * 常用数字格式化工具类
 * 
 * @author Lynch Yao
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class NumberUtil {
	
	private static final Logger log = Logger.getLogger(NumberUtil.class);
	
	/**
	 * 将字符串格式化为整型
	 * 
	 * @param str
	 * @return
	 */
	public static int parseStringToInt(String str) {
		if (str == null || str.trim().length() == 0)
			return 0;
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException nfe) {
			log.error("将字符串格式化为整型出错、该方法将返回0");
			return 0;
		}
	}

	/**
	 * 将字符串格式化为长整型
	 * 
	 * @param str
	 * @return
	 */
	public static Long parseStringToLong(String str) {
		if (str == null || str.trim().length() == 0)
			return 0L;
		try {
			return Long.valueOf(str);
		} catch (NumberFormatException nfe) {
			log.error("将字符串格式化为长整型出错、该方法将返回0");
			return 0L;
		}
	}

	/**
	 * 将字符串格式化为双精度数
	 * 
	 * @param str
	 * @return
	 */
	public static double parseStringToDouble(String str) {
		if (str == null || str.trim().length() == 0)
			return 0;
		try {
			return Double.valueOf(str);
		} catch (NumberFormatException nfe) {
			log.error("将字符串格式化为双精度数出错、该方法将返回0");
			return 0;
		}
	}

	/**
	 * 将整型格式化为长整型
	 * 
	 * @param i
	 * @return
	 */
	public static Long parseIntToLong(int i) {
		try {
			return Long.valueOf(i);
		} catch (NumberFormatException nfe) {
			log.error("将整型格式化为长整型出错、该方法将返回0");
			return 0L;
		}
	}
	
	/**
	 * 将整型格式化为浮点整型
	 * 
	 * @param str
	 * @return
	 */
	public static Float parseStringToFloat(String str) {
		if (str == null || str.trim().length() == 0)
			return 0f;
		try {
			return Float.valueOf(str);
		} catch (NumberFormatException nfe) {
			log.error("将字符串格式化为浮点型出错、该方法将返回0");
			return 0f;
		}
		
	}

}
