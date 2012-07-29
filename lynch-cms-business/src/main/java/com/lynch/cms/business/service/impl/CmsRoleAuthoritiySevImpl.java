package com.lynch.cms.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsRoleAuthoritiyDao;
import com.lynch.cms.business.model.entity.CmsRoleAuthoritiy;
import com.lynch.cms.business.service.CmsRoleAuthoritiySev;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 角色权限业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsRoleAuthoritiySev")
public class CmsRoleAuthoritiySevImpl extends
		BaseServiceImpl<CmsRoleAuthoritiy, Long> implements
		CmsRoleAuthoritiySev {

	private CmsRoleAuthoritiyDao cmsRoleAuthoritiyDao;

	public CmsRoleAuthoritiyDao getCmsRoleAuthoritiyDao() {
		return cmsRoleAuthoritiyDao;
	}

	@Resource
	public void setCmsRoleAuthoritiyDao(
			CmsRoleAuthoritiyDao cmsRoleAuthoritiyDao) {
		super.setBaseDao(cmsRoleAuthoritiyDao);
		this.cmsRoleAuthoritiyDao = cmsRoleAuthoritiyDao;
	}

}
