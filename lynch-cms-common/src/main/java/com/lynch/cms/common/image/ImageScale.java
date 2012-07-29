package com.lynch.cms.common.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.stereotype.Service;


/**
 * 图片等比缩放组件工具类
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
@Service("imageScale")
public class ImageScale {

	/** 图片name初始值 */
	public static int generateCount = 0;
	/** 图片类型 */
	public static String ContentType = "jpg";

	/**
	 * 图片缩放(图片等比例缩放为指定大小，空白部分以白色填充)
	 * 
	 * @param srcBufferedImage 源图片
	 * @param destFile 缩放后的图片文件
	 * @param destWidth  指定的宽度
	 * @param destHeight 指定的高度
	 */
	public boolean scalesImage(BufferedImage srcBufferedImage, File destFile,
			ImageSize imageSize) {

		try {
			int imgWidth = imageSize.getWidth();
			int imgHeight = imageSize.getHeight();
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			double scaleW = imageSize.getWidth() * 1.0 / srcWidth;
			double scaleH = imageSize.getHeight() * 1.0 / srcHeight;
			if (scaleW >= scaleH) {
				double imgWidth1 = scaleH * srcWidth;
				double imgHeight1 = scaleH * srcHeight;
				imgWidth = (int) imgWidth1;
				imgHeight = (int) imgHeight1;
			} else {
				double imgWidth1 = scaleW * srcWidth;
				double imgHeight1 = scaleW * srcHeight;
				imgWidth = (int) imgWidth1;
				imgHeight = (int) imgHeight1;
			}
			BufferedImage destBufferedImage = new BufferedImage(imageSize.getWidth(),
					imageSize.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, imageSize.getWidth(), imageSize.getHeight());
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth,
					imgHeight, Image.SCALE_SMOOTH), (imageSize.getWidth() / 2)
					- (imgWidth / 2), (imageSize.getHeight() / 2) - (imgHeight / 2), null);
			graphics2D.dispose();
			ImageIO.write(destBufferedImage, ContentType, destFile);
			return true;
		} catch (IOException e) {
			System.out.println("文件缩放处理出错!");
			return false;
		}
	}

	/**
	 * 得到不重复的图排序名字
	 * 
	 * @return 图片名称
	 */
	public String getImageName() {

		if (generateCount > 99999) {
			generateCount = 0;
		}
		String uniqueNumber = Long.toString(System.currentTimeMillis())
				+ Integer.toString(generateCount);
		generateCount++;
		return uniqueNumber;
	}

	/**
	 * 获取图片文件的类型.
	 * 
	 * @param imageFile
	 *            图片文件对象.
	 * @return 图片文件类型
	 */
	public String getImageFormatName(File imageFile) {

		try {
			ImageInputStream imageInputStream = ImageIO
					.createImageInputStream(imageFile);
			Iterator<ImageReader> iterator = ImageIO
					.getImageReaders(imageInputStream);
			if (!iterator.hasNext()) {
				return null;
			}
			ImageReader imageReader = (ImageReader) iterator.next();
			imageInputStream.close();
			return imageReader.getFormatName().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}