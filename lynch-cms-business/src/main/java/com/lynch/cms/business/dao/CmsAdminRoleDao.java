package com.lynch.cms.business.dao;

import java.util.List;

import com.lynch.cms.business.model.entity.CmsAdminRole;
import com.lynch.cms.core.dao.BaseDao;

/**
 * 管理员角色业务处理DAO--Inf
 * 
 * @author Lynch
 */
public interface CmsAdminRoleDao extends BaseDao<CmsAdminRole, Long> {

	/**
	 * 根据用户id来得到中间表的用户角色列表 
	 */
	List<CmsAdminRole> getAdminRoleList(Long adminId);

	/**
	 * 删除掉中间表所有此用户的所有的数据 
	 */
	void deleteAdminRole(Long adminId);

}
