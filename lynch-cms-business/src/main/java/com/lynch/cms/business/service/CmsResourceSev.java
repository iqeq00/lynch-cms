package com.lynch.cms.business.service;

import java.util.Map;

import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.core.service.BaseService;

/**
 * 资源业务处理Sev--Inf
 * 
 * @author Lynch
 */
public interface CmsResourceSev extends BaseService<CmsResource, Long> {

	Map<String, Object> pagerListTest(Integer rows, Integer page,
			CmsResource cmsResource);

	/**
	 * 得到资源信息
	 */
	Map<String, Object> getResourceList(Long authoritiyId);
}
