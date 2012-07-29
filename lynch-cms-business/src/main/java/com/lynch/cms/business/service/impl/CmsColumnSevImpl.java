package com.lynch.cms.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsColumnDao;
import com.lynch.cms.business.model.entity.CmsColumn;
import com.lynch.cms.business.service.CmsColumnSev;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 栏目业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsColumnSev")
public class CmsColumnSevImpl extends BaseServiceImpl<CmsColumn, Integer>
		implements CmsColumnSev {

	private CmsColumnDao cmsColumnDao;

	public CmsColumnDao getCmsColumnDao() {
		return cmsColumnDao;
	}

	@Resource
	public void setCmsColumnDao(CmsColumnDao cmsColumnDao) {
		super.setBaseDao(cmsColumnDao);
		this.cmsColumnDao = cmsColumnDao;
	}
	
}
