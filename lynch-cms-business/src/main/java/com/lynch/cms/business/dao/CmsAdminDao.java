package com.lynch.cms.business.dao;

import com.lynch.cms.business.model.entity.CmsAdmin;
import com.lynch.cms.core.dao.BaseDao;


/**
 * 管理员业务处理DAO--Inf
 * 
 * @author Lynch
 */
public interface CmsAdminDao extends BaseDao<CmsAdmin, Long> {

	/**
	 * 根据用户名字查询用户 
	 */
	CmsAdmin getByName(String userName);
	
}
