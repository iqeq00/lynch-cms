package com.lynch.cms.common.file;

import java.io.File;

import javax.servlet.ServletContext;

public class FileUtil {

	/** 图片上传文件保存文件夹 */
	private static String fileUpload = File.separator + "upload";

	/**
	 * 判断一个文件是否存在
	 * 
	 * @param ServletContext context
	 * @param fileName 文件名字
	 * @return boolean 是否存在
	 * @author xiao yao
	 */
	public static boolean fileExists(ServletContext context, String fileName){
		
		boolean isExists = false;
		String realpath = context.getRealPath(fileUpload);
		File savedir = new File(realpath, fileName);
		if(savedir.exists()){
			isExists = true;
		}
		return isExists;
	}
}
