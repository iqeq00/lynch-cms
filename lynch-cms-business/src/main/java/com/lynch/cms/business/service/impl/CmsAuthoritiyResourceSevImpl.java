package com.lynch.cms.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAuthoritiyResourceDao;
import com.lynch.cms.business.model.entity.CmsAuthoritiyResource;
import com.lynch.cms.business.service.CmsAuthoritiyResourceSev;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 权限资源业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsAuthoritiyResourceSev")
public class CmsAuthoritiyResourceSevImpl extends
		BaseServiceImpl<CmsAuthoritiyResource, Long> implements
		CmsAuthoritiyResourceSev {

	private CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao;

	public CmsAuthoritiyResourceDao getCmsAuthoritiyResourceDao() {
		return cmsAuthoritiyResourceDao;
	}

	@Resource
	public void setCmsAuthoritiyResourceDao(
			CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao) {
		super.setBaseDao(cmsAuthoritiyResourceDao);
		this.cmsAuthoritiyResourceDao = cmsAuthoritiyResourceDao;
	}

}
