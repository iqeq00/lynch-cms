package com.lynch.cms.business.service;

import java.util.List;
import java.util.Map;

import com.lynch.cms.business.model.entity.CmsAuthoritiy;
import com.lynch.cms.core.service.BaseService;

/**
 * 权限业务处理Sev--Inf
 * 
 * @author Lynch
 */
public interface CmsAuthoritiySev extends BaseService<CmsAuthoritiy, Long> {

	Map<String, Object> pagerListTest(Integer rows, Integer page);

	void updateAuthoritiyResource(Long authoritiyId, List<Long> resourceIds);

	/**
	 * 得到权限信息
	 */
	Map<String, Object> getAuthoritiyList(Long roleId);

}
