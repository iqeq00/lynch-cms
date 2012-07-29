package com.lynch.cms.common.html;

import java.io.File;

/**
 * 静态化html文件夹 
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class HtmlFolder {

	/**文件夹符号*/
	public static final String separator = File.separator;
	
	/**首页静态模版路径*/
	public static final String indexFtl = separator + "index" + separator;	
	/**首页静态页面路径*/
	public static final String indexHtml = separator + "html" + separator + "index";
	
	/**商品静态模版路径*/
	public static final String productFtl = separator + "index" + separator;
	/**商品静态页面路径*/
	public static final String productHtml = separator + "html" + separator + "product";
	
	
	/**测试静态模版路径*/
	public static final String testFtl = separator + "ftlHtml" + separator + "ftl" + separator;
	/**测试静态页面路径*/
	public static final String testHtml = separator + "ftlHtml" + separator + "html" ;
	
	
	
	/**
	 * 首页包含静态化（分别对应每个需要生成静态化的页面）
	 * 
	 * 头部、友情链接、广告、客服、尾巴。
	 */
	public static final String HEAD = "head.html";
	public static final String LINK = "link.html";
	public static final String ADMENT = "adment.html";
	public static final String CUSTOMERSERVICE = "customerService.html";
	public static final String BOTTOM = "bottom.html";
	
	
}
