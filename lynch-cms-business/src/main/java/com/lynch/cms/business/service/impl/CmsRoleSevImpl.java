package com.lynch.cms.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAdminRoleDao;
import com.lynch.cms.business.dao.CmsAuthoritiyDao;
import com.lynch.cms.business.dao.CmsRoleAuthoritiyDao;
import com.lynch.cms.business.dao.CmsRoleDao;
import com.lynch.cms.business.model.entity.CmsAdminRole;
import com.lynch.cms.business.model.entity.CmsAuthoritiy;
import com.lynch.cms.business.model.entity.CmsRole;
import com.lynch.cms.business.model.entity.CmsRoleAuthoritiy;
import com.lynch.cms.business.service.CmsRoleSev;
import com.lynch.cms.core.model.vo.Pager;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 角色业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsRoleSev")
public class CmsRoleSevImpl extends BaseServiceImpl<CmsRole, Long> implements
		CmsRoleSev {

	private CmsRoleDao cmsRoleDao;
	private CmsRoleAuthoritiyDao cmsRoleAuthoritiyDao;
	private CmsAuthoritiyDao cmsAuthoritiyDao;
	private CmsAdminRoleDao cmsAdminRoleDao;

	public Map<String, Object> pagerListTest(Integer rows, Integer page,
			CmsRole cmsRole) {

		Pager<CmsRole> pager = this.pagerList(rows, page);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("total", pager.getAllRow());
		result.put("rows", pager.getList());
		return result;
	}

	public void updateRoleAuthoritiy(Long roleId, List<Long> authoritiyIds) {

		// 删除掉中间表所有此角色的所有的数据
		cmsRoleAuthoritiyDao.deleteRoleAuthoritiy(roleId);
		// 得到角色对象
		CmsRole cmsRole = cmsRoleDao.get(roleId);
		// 存放角色权限的set
		Set<CmsRoleAuthoritiy> cmsRoleAuthoritiys = new HashSet<CmsRoleAuthoritiy>();
		// 循环将创建所有的权限资源信息
		Iterator<Long> iterator = authoritiyIds.iterator();
		while (iterator.hasNext()) {
			Long authoritiyId = (Long) iterator.next();
			CmsAuthoritiy cmsAuthoritiy = cmsAuthoritiyDao.get(authoritiyId);
			CmsRoleAuthoritiy cmsRoleAuthoritiy = new CmsRoleAuthoritiy();
			cmsRoleAuthoritiy.setCmsRole(cmsRole);
			cmsRoleAuthoritiy.setCmsAuthoritiy(cmsAuthoritiy);
			cmsRoleAuthoritiy.setRoleAuthoritiyCreateTime(new Date());
			cmsRoleAuthoritiy.setEnabled(true);
			cmsRoleAuthoritiys.add(cmsRoleAuthoritiy);

		}
		cmsRole.setRoleAuthorities(cmsRoleAuthoritiys);
		cmsRoleDao.update(cmsRole);
	}

	/**
	 * 根据用户id来得到此用户的角色列表
	 */
	public Map<String, Object> getRoleList(Long adminId) {

		Map<String, Object> result = new HashMap<String, Object>(2);
		List<CmsRole> roleList = cmsRoleDao.loadAll();
		List<CmsAdminRole> adminRoleList = cmsAdminRoleDao
				.getAdminRoleList(adminId);
		for (int i = 0; i < roleList.size(); i++) {
			CmsRole cmsRole = roleList.get(i);
			if (adminRoleList != null && adminRoleList.size() > 0) {
				for (int j = 0; j < adminRoleList.size(); j++) {
					CmsAdminRole cmsAdminRole = adminRoleList.get(j);
					if (cmsRole.getRoleId() == cmsAdminRole.getCmsRole()
							.getRoleId()) {
						cmsRole.setEnabled(true);
						break;
					} else {
						cmsRole.setEnabled(false);
					}
				}
			} else {
				cmsRole.setEnabled(false);
			}
		}
		result.put("rows", roleList);
		return result;
	}

	public CmsRoleDao getCmsRoleDao() {
		return cmsRoleDao;
	}

	@Resource
	public void setCmsRoleDao(CmsRoleDao cmsRoleDao) {
		super.setBaseDao(cmsRoleDao);
		this.cmsRoleDao = cmsRoleDao;
	}

	public CmsRoleAuthoritiyDao getCmsRoleAuthoritiyDao() {
		return cmsRoleAuthoritiyDao;
	}

	@Resource
	public void setCmsRoleAuthoritiyDao(
			CmsRoleAuthoritiyDao cmsRoleAuthoritiyDao) {
		this.cmsRoleAuthoritiyDao = cmsRoleAuthoritiyDao;
	}

	public CmsAuthoritiyDao getCmsAuthoritiyDao() {
		return cmsAuthoritiyDao;
	}

	@Resource
	public void setCmsAuthoritiyDao(CmsAuthoritiyDao cmsAuthoritiyDao) {
		this.cmsAuthoritiyDao = cmsAuthoritiyDao;
	}

	public CmsAdminRoleDao getCmsAdminRoleDao() {
		return cmsAdminRoleDao;
	}

	@Resource
	public void setCmsAdminRoleDao(CmsAdminRoleDao cmsAdminRoleDao) {
		this.cmsAdminRoleDao = cmsAdminRoleDao;
	}

}
