package com.lynch.cms.common.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

/**
 * 图片上传组件工具类
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
@Service("imageUpload")
public class ImageUpload {

	/** 图片缩放 */
	private ImageScale imageScale;
	/** 图片上传文件保存文件夹 */
	private static String fileUpload = File.separator + "upload";//".." + File.separator + "photoUpload";
	/** 图片文件存放路径 */
	private String realpath;

	/**
	 * 图片上传 only file
	 * 
	 * @param ServletContext context
	 * @param File image 文件
	 * @param String fileName 文件名
	 * @return String 上传后的文件名
	 * @author xiao yao
	 * @throws IOException
	 */
	public String imageUpload(ServletContext context, File image, String fileName, ImageSize imageSize) {

		try {
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				fileName = imageScale.getImageName() + "." + imageScale.ContentType;
				BufferedImage src = ImageIO.read(image);
				File saveFile = new File(savedir, fileName);
				imageScale.scalesImage(src, saveFile, imageSize);
			}
			return fileName;
		} catch (IOException e) {
			System.out.println("上传图片文件出错!");
			return null;
		}
	}
	
	/**
	 * 图片上传 only file
	 * 
	 * @param ServletContext context
	 * @param File image 文件
	 * @param String fileName 文件名
	 * @return String 上传后的文件名
	 * @author xiao yao
	 * @throws IOException
	 */
	public String imageUpload(ServletContext context, File image, String fileName, List<ImageSize> list) {

		try {
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				fileName = imageScale.getImageName() + "." + imageScale.ContentType;
				BufferedImage src = ImageIO.read(image);
				for (int j = 0; j < list.size(); j++) {
					ImageSize imageSize = list.get(j);
					String imageName = null;
					if (j == 0) {
						imageName = fileName;
					} else {
						imageName = j + fileName;
					}
					File saveFile = new File(savedir, imageName);
					imageScale.scalesImage(src, saveFile, imageSize);
				}
			}
			return fileName;
		} catch (IOException e) {
			System.out.println("上传图片文件出错!");
			return null;
		}
	}
	
	/**
	 * 图片上传 many file
	 * 
	 * @param ServletContext context
	 * @param File[] image 文件数组
	 * @param String[] fileName 文件名数组
	 * @return boolean 上传是否成功
	 * @author xiao yao
	 * @throws IOException
	 */
	public boolean imageUpload(ServletContext context, File[] image, String[] fileName, List<ImageSize> list) {

		try {
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				for (int i = 0; i < image.length; i++) {
					fileName[i] = imageScale.getImageName() + "." + imageScale.ContentType;
					BufferedImage src = ImageIO.read(image[i]);
					for (int j = 0; j < list.size(); j++) {
						ImageSize imageSize = list.get(j);
						String imageName = null;
						if (j == 0) {
							imageName = fileName[i];
						} else {
							imageName = j + fileName[i];
						}
						File saveFile = new File(savedir, imageName);
						imageScale.scalesImage(src, saveFile, imageSize);
					}
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println("上传图片文件出错!");
			return false;
		}
	}

	/**
	 * 图片上传 many file
	 * 
	 * @param ServletContext context
	 * @param List<File> image 文件list
	 * @param List<String> fileName 文件名list
	 * @return boolean 上传是否成功
	 * @author xiao yao
	 * @throws IOException
	 */
	public boolean imageUpload(ServletContext context, List<File> image, List<String> fileName, List<ImageSize> list) {

		try {
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				for (int i = 0; i < image.size(); i++) {
					BufferedImage src = ImageIO.read(image.get(i));
					String imageName = imageScale.getImageName() + "." + imageScale.ContentType;
					for (int j = 0; j < list.size(); j++) {
						ImageSize imageSize = list.get(j);
						String imageNameTemp = null;
						if (j == 0) {
							imageNameTemp = imageName;
						} else {
							imageNameTemp = j + imageName;
						}
						File saveFile = new File(savedir, imageNameTemp);
						imageScale.scalesImage(src, saveFile, imageSize);
					}
					fileName.set(i, imageName);
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println("上传图片文件出错!"+e.getMessage());
			return false;
		}
	}
	
	/**
	 * 图片上传 only file
	 * 
	 * @param ServletContext context
	 * @param File image 文件
	 * @param String fileName 文件名
	 * @return String 上传后的文件名
	 * @author xiao yao
	 * @throws IOException
	 */
	public String imageUpload(ServletContext context, File image, String fileName){    
		
		try {          
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);         
				if(!savedir.exists()){             
					savedir.mkdirs();         
				}         
				fileName = imageScale.getImageName() + "." + imageScale.ContentType;
				FileUtils.copyFile(image, new File(savedir,fileName)); 
			}
			return fileName;     
		} catch (IOException e) {             
			System.out.println("上传图片文件出错!"+e.getMessage());
			return null;
		}         
	}  
	
	/**
	 * 图片上传 many file
	 * 
	 * @param ServletContext context
	 * @param File[] image 文件数组
	 * @param String[] fileName 文件名数组
	 * @return boolean 上传是否成功
	 * @author xiao yao
	 * @throws IOException
	 */
	public boolean imageUpload(ServletContext context, File[] image, String[] fileName){    
		
		try {          
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);         
				if(!savedir.exists()){             
					savedir.mkdirs();         
				}    
				for (int i = 0; i < image.length; i++) {
					fileName[i] = imageScale.getImageName() + "." + imageScale.ContentType;
					FileUtils.copyFile(image[i], new File(savedir,fileName[i])); 
				}
			}
			return true;     
		} catch (IOException e) {             
			System.out.println("上传图片文件出错!"+e.getMessage());
			return false;
		}         
	}  
	
	/**
	 * 图片上传 many file
	 * 
	 * @param ServletContext context
	 * @param File[] image 文件数组
	 * @param String[] fileName 文件名数组
	 * @return boolean 上传是否成功
	 * @author xiao yao
	 * @throws IOException
	 */
	public boolean imageUploadName(ServletContext context, File[] image, String[] fileName){    
		
		try {          
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);         
				if(!savedir.exists()){             
					savedir.mkdirs();         
				}    
				for (int i = 0; i < image.length; i++) {
					FileUtils.copyFile(image[i], new File(savedir,fileName[i])); 
				}
			}
			return true;     
		} catch (IOException e) {             
			System.out.println("上传图片文件出错!"+e.getMessage());
			return false;
		}         
	}  
	
	/**
	 * 图片上传 many file
	 * 
	 * @param ServletContext context
	 * @param List<File> image 文件list
	 * @param List<String> fileName 文件名list
	 * @return boolean 上传是否成功
	 * @author xiao yao
	 * @throws IOException
	 */
	public boolean imageUpload(ServletContext context, List<File> image, List<String> fileName){ 
		
		try {          
			if (image != null) {
				realpath = context.getRealPath(fileUpload);
				File savedir = new File(realpath);         
				if(!savedir.exists()){             
					savedir.mkdirs();         
				}    
				for (int i = 0; i < image.size(); i++) {
					String imageName = imageScale.getImageName() + "." + imageScale.ContentType;
					FileUtils.copyFile(image.get(i), new File(savedir,imageName)); 
					fileName.set(i, imageName);
				}
			}
			return true;     
		} catch (IOException e) {             
			System.out.println("上传图片文件出错!"+e.getMessage());
			return false;
		}         
	}
	
	public ImageScale getImageScale() {
		return imageScale;
	}

	@Resource
	public void setImageScale(ImageScale imageScale) {
		this.imageScale = imageScale;
	}

}
