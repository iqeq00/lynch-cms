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

import com.lynch.cms.business.model.entity.CmsAuthoritiy;
import com.lynch.cms.business.service.CmsAuthoritiySev;

/**
 * 权限控制层处理
 * 
 * @author Lynch
 */
@Controller
@RequestMapping(value = "/admin")
public class CmsAuthoritiyCol {

	private CmsAuthoritiySev cmsAuthoritiySev;

	/**
	 * 查询权限列表
	 * 
	 * @param rows 每页显示条目
	 * @param page 当前第几页
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/authoritiyList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> authoritiyList(Integer rows, Integer page) {

		return cmsAuthoritiySev.pagerListTest(rows, page);
	}

	/**
	 * 新增权限
	 * 
	 * @param cmsAuthoritiy 权限对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/authoritiyAdd.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> authoritiyAdd(CmsAuthoritiy cmsAuthoritiy) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAuthoritiy.setEnabled(true);
			cmsAuthoritiy.setAuthoritiyCreateTime(new Date());
			cmsAuthoritiySev.save(cmsAuthoritiy);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("message", "保存失败");
		}
		return map;
	}

	/**
	 * 更新权限信息
	 * 
	 * @param cmsAuthoritiy 权限对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/authoritiyUpdate.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> authoritiyUpdate(CmsAuthoritiy cmsAuthoritiy) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAuthoritiy.setEnabled(true);
			cmsAuthoritiy.setAuthoritiyUpadteTime(new Date());
			cmsAuthoritiySev.update(cmsAuthoritiy);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}

	/**
	 * 设置权限可访问的资源信息
	 * 
	 * @param authoritiyId 权限PK
	 * @param resourceIds 可访问资源的PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/authoritiyResourceSet.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> authoritiyResourceSet(Long authoritiyId,
			@RequestParam("resourceIds") List<Long> resourceIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAuthoritiySev.updateAuthoritiyResource(authoritiyId, resourceIds);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}

	/**
	 * 删除权限
	 * 
	 * @param authoritiyIds 权限PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/authoritiyDelete.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> authoritiyDelete(
			@RequestParam("authoritiyIds") List<Long> authoritiyIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAuthoritiySev.deleteAllByPKs(authoritiyIds);
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("message", "删除失败");
		}
		return map;
	}

	/**
	 * 查询权限列表
	 * 
	 * @param rows 每页显示条目
	 * @param page 当前第几页
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/roleAuthoritiyList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> roleAuthoritiyList(Long roleId) {

		return cmsAuthoritiySev.getAuthoritiyList(roleId);
	}

	public CmsAuthoritiySev getCmsAuthoritiySev() {
		return cmsAuthoritiySev;
	}

	@Resource
	public void setCmsAuthoritiySev(CmsAuthoritiySev cmsAuthoritiySev) {
		this.cmsAuthoritiySev = cmsAuthoritiySev;
	}

}
