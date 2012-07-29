package com.lynch.cms.common.html;

import java.util.Map;

/**
 * 静态化html选项类 
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class HtmlItem {
	
	/**存放模版动态数据的map*/
	private Map<String,Object> values;
	/**ftl文件模版路径*/
	private String ftlPath;
	/**ftl文件名称*/
	private String ftlName;
	/**html文件生成路径*/
	private String htmlPath;
	/**html文件生成名字*/
	private String htmlName;
	
	/**构造方法*/
	public HtmlItem() {
		
	}
	
	/**构造方法*/
	public HtmlItem(Map<String, Object> values, String ftlPath, String ftlName,
			String htmlPath, String htmlName) {
		
		this.values = values;
		this.ftlPath = ftlPath;
		this.ftlName = ftlName;
		this.htmlPath = htmlPath;
		this.htmlName = htmlName;
	}

	/**get、set方法*/
	public Map<String, Object> getValues() {
		return values;
	}
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	public String getFtlPath() {
		return ftlPath;
	}
	public void setFtlPath(String ftlPath) {
		this.ftlPath = ftlPath;
	}
	public String getFtlName() {
		return ftlName;
	}
	public void setFtlName(String ftlName) {
		this.ftlName = ftlName;
	}
	public String getHtmlPath() {
		return htmlPath;
	}
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	public String getHtmlName() {
		return htmlName;
	}
	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}
	
	
	

}
