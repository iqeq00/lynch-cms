package com.lynch.cms.business.col.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lynch.cms.business.model.entity.CmsAdmin;
import com.lynch.cms.business.model.vo.NodeTree;
import com.lynch.cms.business.service.CmsAdminSev;

/**
 * 管理员控制层处理
 * 
 * @author Lynch
 */
@Controller
@RequestMapping(value = "/admin")
public class CmsAdminCol {

	private CmsAdminSev cmsAdminSev;
	private Md5PasswordEncoder passwordEncoder;

	/**
	 * 查询管理员列表
	 * 
	 * @param rows 每页显示条目
	 * @param page 当前第几页
	 * @return map 包含2个key-value(1.总记录数。2.当前页面的list)
	 */
	@RequestMapping(value = "/adminList.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> adminList(Integer rows, Integer page,
			CmsAdmin cmsAdmin) {

		return cmsAdminSev.pagerListTest(rows, page, cmsAdmin);
	}

	/**
	 * 新增管理员
	 * 
	 * @param cmsAdmin 管理员对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/adminAdd.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminAdd(CmsAdmin cmsAdmin) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAdmin.setEnabled(true);
			cmsAdmin.setAdminCreateTime(new Date());
			cmsAdmin.setAdminPassword(passwordEncoder.encodePassword(cmsAdmin.getAdminPassword(), cmsAdmin.getAdminName()));
			cmsAdminSev.save(cmsAdmin);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("message", "保存失败");
		}
		return map;
	}

	/**
	 * 更新管理员
	 * 
	 * @param cmsAdmin 管理员对象
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/adminUpdate.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminUpdate(CmsAdmin cmsAdmin) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAdmin.setEnabled(true);
			cmsAdmin.setAdminUpadteTime(new Date());
			cmsAdmin.setAdminPassword(passwordEncoder.encodePassword(cmsAdmin.getAdminPassword(), cmsAdmin.getAdminName()));
			cmsAdminSev.update(cmsAdmin);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}

	/**
	 * 删除管理员
	 * 
	 * @param adminIds 管理员PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/adminDelete.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminDelete(
			@RequestParam("adminIds") List<Long> adminIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAdminSev.deleteAllByPKs(adminIds);
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("message", "删除失败");
		}
		return map;
	}

	/**
	 * 设置用户的角色信息
	 * 
	 * @param adminId 管理员PK
	 * @param roleIds 角色PK集合
	 * @return map 包含1个key-value(操作是否成功)
	 */
	@RequestMapping(value = "/adminRoleSet.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminRoleSet(Long adminId,
			@RequestParam("roleIds") List<Long> roleIds) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			cmsAdminSev.updateAdminRole(adminId, roleIds);
			map.put("message", "修改成功");
		} catch (Exception e) {
			map.put("message", "修改失败");
		}
		return map;
	}
	
	/**
	 * 异步获取用户栏目树 
	 * 
	 */
	@RequestMapping(value = "/adminAsyncTree.jspx", method = RequestMethod.POST)
	@ResponseBody
	public List<NodeTree> adminAsyncTree(){
		
//		List<NodeTree> list = new ArrayList<NodeTree>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("url", "admin/adminList.jsp");
//		Set<NodeTree> set = new HashSet<NodeTree>();
//		NodeTree nodeUi = new NodeTree(2,"管理员管理",null,map);
//		set.add(nodeUi);
//		NodeTree nodeUi1 = new NodeTree(1,"管理员管理",set,null);
//		list.add(nodeUi1);
	
//		System.out.println("111");
		return cmsAdminSev.getAsyncTree();
	}
	

	public CmsAdminSev getCmsAdminSev() {
		return cmsAdminSev;
	}

	@Resource
	public void setCmsAdminSev(CmsAdminSev cmsAdminSev) {
		this.cmsAdminSev = cmsAdminSev;
	}

	public Md5PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Resource
	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
