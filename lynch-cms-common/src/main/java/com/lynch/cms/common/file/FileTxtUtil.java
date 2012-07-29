package com.lynch.cms.common.file;

import java.io.*;

public class FileTxtUtil {

	/**
	 * TXT文件工具类
	 * 
	 * @author Lynch Yao
	 * @email : iqeq00@163.com
	 * @version 1.0
	 */

	public static BufferedReader bufread;

	/** 指定文件路径和名称 */
	private static String path = "/com/hsay/util/filter.txt";
	private static File filename = new File(FileTxtUtil.class.getResource(path).getPath());
	private static String readStr = "";

	/**
	 * 创建文本文件.
	 * 
	 * @throws IOException
	 */
	public static void creatTxtFile() throws IOException {
		if (!filename.exists()) {
			filename.createNewFile();
			System.err.println(filename + "已创建！");
		}
	}

	/**
	 * 读取文本文件.
	 * 
	 * @return String 读取文本的字符串
	 */

	public static String readTxtFile() {
		String read;
		FileReader fileread;
		try {
			fileread = new FileReader(filename);
			bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = read;
				}
				bufread.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return readStr;
	}

	/**
	 * 写入文本文件.
	 * 
	 * @param newStr 要写入的字符串
	 * @throws IOException
	 */

	public static void writeTxtFile(String newStr) throws IOException {

		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, false);
			fw.write(newStr + "；");
		} catch (Exception e) {
			System.out.println("写入发生错误...");
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
