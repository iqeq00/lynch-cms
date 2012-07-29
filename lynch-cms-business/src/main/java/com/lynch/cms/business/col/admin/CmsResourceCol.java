package com.lynch.cms.business.col.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.business.service.CmsResourceSev;

/**
 * 资源控制层处理
 * 
 * @author Lynch
 */
@Controller
@RequestMapping(value = "/admin")
public class CmsResourceCol {

	private CmsResourceSev cmsResourceSev;

	/**
	 * 查询资源列表
	 * 
	 * @param rows 每页显示条目
	 * @param page 当前第几页
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/resourceList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resourceList(Integer rows, Integer page,
			CmsResource cmsResource) {

		return cmsResourceSev.pagerListTest(rows, page, cmsResource);
	}

	/**
	 * 新增资源
	 * 
	 * @param cmsResource 资源对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/resourceAdd.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> resourceAdd(CmsResource cmsResource) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsResource.setEnabled(true);
			cmsResource.setResourceCreateTime(new Date());
			cmsResourceSev.save(cmsResource);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("message", "保存失败");
		}
		return map;
	}

	/**
	 * 更新资源
	 * 
	 * @param cmsResource 资源对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/resourceUpdate.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> resourceUpdate(CmsResource cmsResource) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsResource.setEnabled(true);
			cmsResource.setResourceUpadteTime(new Date());
			cmsResourceSev.update(cmsResource);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}

	/**
	 * 删除资源
	 * 
	 * @param resourceIds 资源PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/resourceDelete.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> resourceDelete(
			@RequestParam("resourceIds") List<Long> resourceIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsResourceSev.deleteAllByPKs(resourceIds);
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("message", "删除失败");
		}
		return map;
	}

	/**
	 * 查询资源列表
	 * 
	 * @param rows 每页显示条目
	 * @param page 当前第几页
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/authoritiyResourceList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> authoritiyResourceList(Long authoritiyId) {

		return cmsResourceSev.getResourceList(authoritiyId);
	}

	public CmsResourceSev getCmsResourceSev() {
		return cmsResourceSev;
	}

	@Resource
	public void setCmsResourceSev(CmsResourceSev cmsResourceSev) {
		this.cmsResourceSev = cmsResourceSev;
	}

}
