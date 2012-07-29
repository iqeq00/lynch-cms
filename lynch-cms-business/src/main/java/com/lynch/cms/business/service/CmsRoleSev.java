package com.lynch.cms.business.service;

import java.util.List;
import java.util.Map;

import com.lynch.cms.business.model.entity.CmsRole;
import com.lynch.cms.core.service.BaseService;

/**
 * 角色业务处理Sev--Inf
 * 
 * @author Lynch
 */
public interface CmsRoleSev extends BaseService<CmsRole, Long> {

	Map<String, Object> pagerListTest(Integer rows, Integer page,
			CmsRole cmsRole);

	void updateRoleAuthoritiy(Long roleId, List<Long> authoritiyIds);

	/**
	 * 根据用户id来得到此用户的角色列表
	 */
	Map<String, Object> getRoleList(Long adminId);

}
