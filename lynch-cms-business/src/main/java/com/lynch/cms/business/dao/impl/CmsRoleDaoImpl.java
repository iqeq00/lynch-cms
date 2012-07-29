package com.lynch.cms.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsRoleDao;
import com.lynch.cms.business.model.entity.CmsRole;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 角色业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsRoleDao")
public class CmsRoleDaoImpl extends BaseDaoImpl<CmsRole, Long> implements
		CmsRoleDao {

}
