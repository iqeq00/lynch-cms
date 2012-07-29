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

import com.lynch.cms.business.model.entity.CmsRole;
import com.lynch.cms.business.service.CmsRoleSev;

/**
 * 角色控制层处理
 * 
 * @author Lynch
 */
@Controller
@RequestMapping(value = "/admin")
public class CmsRoleCol {

	private CmsRoleSev cmsRoleSev;

	/**
	 * 查询角色列表
	 * 
	 * @param rows 每页显示条目
	 * @param page 当前第几页
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/roleList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> authoritiyList(Integer rows, Integer page,
			CmsRole cmsRole) {

		return cmsRoleSev.pagerListTest(rows, page, cmsRole);
	}

	/**
	 * 新增角色
	 * 
	 * @param cmsRole 角色对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/roleAdd.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> roleAdd(CmsRole cmsRole) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsRole.setEnabled(true);
			cmsRole.setRoleCreateTime(new Date());
			cmsRoleSev.save(cmsRole);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("message", "保存失败");
		}
		return map;
	}

	/**
	 * 更新角色
	 * 
	 * @param cmsRole 角色对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/roleUpdate.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> roleUpdate(CmsRole cmsRole) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsRole.setEnabled(true);
			cmsRole.setRoleUpadteTime(new Date());
			cmsRoleSev.update(cmsRole);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}

	/**
	 * 删除角色
	 * 
	 * @param roleIds 角色PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/roleDelete.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> roleDelete(
			@RequestParam("roleIds") List<Long> roleIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsRoleSev.deleteAllByPKs(roleIds);
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("message", "删除失败");
		}
		return map;
	}

	/**
	 * 查询角色列表
	 * 
	 * @param adminId 管理员PK
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/adminRoleList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> adminRoleList(Long adminId) {

		return cmsRoleSev.getRoleList(adminId);
	}

	/**
	 * 设置角色可拥有的权限信息
	 * 
	 * @param roleId 角色PK
	 * @param authoritiyIds 权限PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/roleAuthoritiySet.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> authoritiyResourceSet(Long roleId,
			@RequestParam("authoritiyIds") List<Long> authoritiyIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsRoleSev.updateRoleAuthoritiy(roleId, authoritiyIds);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}

	public CmsRoleSev getCmsRoleSev() {
		return cmsRoleSev;
	}

	@Resource
	public void setCmsRoleSev(CmsRoleSev cmsRoleSev) {
		this.cmsRoleSev = cmsRoleSev;
	}

}
