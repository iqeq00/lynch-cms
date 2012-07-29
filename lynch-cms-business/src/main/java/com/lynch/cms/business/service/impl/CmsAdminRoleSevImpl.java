package com.lynch.cms.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAdminRoleDao;
import com.lynch.cms.business.model.entity.CmsAdminRole;
import com.lynch.cms.business.service.CmsAdminRoleSev;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 管理员角色业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsAdminRoleSev")
public class CmsAdminRoleSevImpl extends BaseServiceImpl<CmsAdminRole, Long>
		implements CmsAdminRoleSev {

	private CmsAdminRoleDao cmsAdminRoleDao;

	public CmsAdminRoleDao getCmsAdminRoleDao() {
		return cmsAdminRoleDao;
	}

	@Resource
	public void setCmsAdminRoleDao(CmsAdminRoleDao cmsAdminRoleDao) {
		super.setBaseDao(cmsAdminRoleDao);
		this.cmsAdminRoleDao = cmsAdminRoleDao;
	}

}
