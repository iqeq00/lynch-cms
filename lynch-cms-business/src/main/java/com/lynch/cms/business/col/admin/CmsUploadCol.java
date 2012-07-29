package com.lynch.cms.business.col.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lynch.cms.common.image.ImageUpload;

@Controller
@RequestMapping(value = "/admin")
public class CmsUploadCol {
	
	private ImageUpload imageUpload;

	/**
	 * 单文件上传
	 * 
	 * @param name
	 * @RequestParam 取得name字段的值
	 * @param file
	 *            文件
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/oneFileUpload.jspx", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) throws IOException {

		System.out.println("哈哈");
		if (!file.isEmpty()) {
			this.copyFile(file.getInputStream(), file.getOriginalFilename());
//			imageUpload.imageUpload(context, image, fileName)

		}
		System.out.println("11111");
		return "admin";
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/multipartFileUpload", method = RequestMethod.POST)
	public String upload2(MultipartHttpServletRequest request,
			@RequestParam("name") String name // 页面上的控件值
	) throws Exception {
		List<MultipartFile> files = request.getFiles("file");
		for (int i = 0; i < files.size(); i++) {
			if (!files.get(i).isEmpty()) {
				this.copyFile(files.get(i).getInputStream(), files.get(i)
						.getOriginalFilename());
			}
		}
		return "fileUpload/success";
	}

	public ImageUpload getImageUpload() {
		return imageUpload;
	}

	@Resource
	public void setImageUpload(ImageUpload imageUpload) {
		this.imageUpload = imageUpload;
	}

	/**
	 * 写文件到本地
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private void copyFile(InputStream in, String fileName) throws IOException {
		
		FileOutputStream fs = new FileOutputStream("d:/upload/" + fileName);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = in.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		in.close();
	}
	
	
}
