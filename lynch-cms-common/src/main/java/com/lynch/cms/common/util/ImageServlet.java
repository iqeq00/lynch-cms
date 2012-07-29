package com.lynch.cms.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码servlet
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class ImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/**验证码的一些常用属性*/
	private static final int ImageWidth = 70;  // 验证码图片宽度
	private static final int ImageHeight = 30;	// 验证码图片高度
	private static final int CodeLength = 4;	// 设置生成验证码位数

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int width = ImageWidth;			// 验证码图片宽度
		int height = ImageHeight;		// 验证码图片高度
		//构造图像数据缓冲区(一个具有 8 位 RGB 颜色分量的图像)
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		//得到绘制图像类，并进行图像绘制
		Graphics g = image.getGraphics();
		Random random = new Random();// 创建一个随机类
		g.setColor(getRandColor(200, 250));// 背景颜色要偏淡
		g.fillRect(0, 0, width, height);// 画背景
		g.setColor(getRandColor(0, 255));// 边框颜色
		g.drawRect(0, 0, width - 1, height - 1);// 画边框
		g.setColor(getRandColor(160, 200));// 随机产生5条干扰线，使图象中的认证码不易被其它程序探测到
		
		for (int i = 0; i < 8; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			g.drawLine(x, y, x1, y1);
		}
		g.setColor(getRandColor(160, 200));// 随机产生100点，使图象中的认证码不易被其它程序探测到
		
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawLine(x, y, x, y);
		}
		Font font = new Font("Times New Roman", Font.ITALIC, 20); // 创建字体，字体的大小应该根据图片的高度来定。
		g.setFont(font);// 设置字体
		int length = CodeLength; // 设置默认生成4个验证码
		String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 设置备选验证码:包括"a-z"和数字"0-9"
		String sRand = "";

		// 用随机产生的颜色将验证码绘制到图像中。
		// 生成随机颜色(因为是做前景，所以偏深)
		// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		for (int i = 0; i < length; i++) {
			String ch = String.valueOf(s.charAt(random.nextInt(s.length())));
			sRand += ch;
			//设置验证码图片的显示和文字
			g.drawString(ch, 11 * i + 6, (random.nextInt(5) - 2) * i + 20);
		}
		// 将生成的字符串存储在session中
		HttpSession session = request.getSession();
		session.setAttribute("checkCode", sRand);
		g.dispose();// 图像生效
		// 禁止图像缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 创建二进制的输出流
		ServletOutputStream sos = response.getOutputStream();
		// 将图像输出到Servlet输出流中。
		ImageIO.write(image, "jpeg", sos);
		sos.flush();
		sos.close();
	}

	/**用指定的红色、绿色、蓝色和 alpha 值创建一种 sRGB 颜色，这些值都在 0-255 的范围内*/
	public Color getRandColor(int lower, int upper) {
		Random random = new Random();
		if (upper > 255){
			upper = 255;
		}
		if (upper < 1){
			upper = 1;
		}
		if (lower < 1){
			lower = 1;
		}
		if (lower > 255){
			lower = 255;
		}
		int r = lower + random.nextInt(upper - lower);
		int g = lower + random.nextInt(upper - lower);
		int b = lower + random.nextInt(upper - lower);
		return new Color(r, g, b);
	}
}