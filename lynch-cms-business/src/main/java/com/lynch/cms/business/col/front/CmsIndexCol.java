package com.lynch.cms.business.col.front;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 管理员控制层处理
 * 
 * @author Lynch
 */
@Scope("prototype")
@Controller("cmsIndexCol")
@RequestMapping(value = "/index")
public class CmsIndexCol {

	/**
	 * 新增资源
	 * 
	 * @param cmsAdmin 管理员对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/testIndex.jspx")
	@ResponseBody
	public String resourceAdd() {
		return "testIndex"; 
	}
}
