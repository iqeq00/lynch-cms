package com.lynch.cms.business.dao;

import java.util.List;

import com.lynch.cms.business.model.entity.CmsRoleAuthoritiy;
import com.lynch.cms.core.dao.BaseDao;

/**
 * 角色权限业务处理DAO--Inf
 * 
 * @author Lynch
 */
public interface CmsRoleAuthoritiyDao extends BaseDao<CmsRoleAuthoritiy, Long> {

	/**
	 * 根据角色id查询所有此角色id的权限
	 */
	List<CmsRoleAuthoritiy> getRoleAuthoritiyList(Long roleId);

	void deleteRoleAuthoritiy(Long roleId);
}
