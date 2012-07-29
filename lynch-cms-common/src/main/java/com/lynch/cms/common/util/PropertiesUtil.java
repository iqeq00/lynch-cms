package com.lynch.cms.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	private Properties props;
	private String fileName;
	public PropertiesUtil(String fileName){
		this.fileName=fileName;
		readProperties(fileName);
	}
	private void readProperties(String fileName) {
		try {
			props = new Properties();
			FileInputStream fis =new FileInputStream(fileName);
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取某个属性
	 */
	public String getProperty(String key){
		return props.getProperty(key);
	}
	/**
	 * 获取所有属性，返回一个map,不常用
	 * 可以试试props.putAll(t)
	 */
	public Map getAllProperty(){
		Map map=new HashMap();
		Enumeration enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 在控制台上打印出所有属性，调试时用。
	 */
	public void printProperties(){
		props.list(System.out);
	}
	/**
	 * 写入properties信息
	 */
    public void writeProperties(String key, String value) {
		try {
			OutputStream fos = new FileOutputStream(fileName);
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (IOException e) {
		}
	}   
	public static void main(String[] args) {
		PropertiesUtil util=new PropertiesUtil("src/config.properties");
		System.out.println("ip=" + util.getProperty("ip"));
		util.writeProperties("key", "value0");
	}    
}