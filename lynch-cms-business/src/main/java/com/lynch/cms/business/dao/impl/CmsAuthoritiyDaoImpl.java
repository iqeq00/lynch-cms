package com.lynch.cms.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsAuthoritiyDao;
import com.lynch.cms.business.model.entity.CmsAuthoritiy;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 权限业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsAuthoritiyDao")
public class CmsAuthoritiyDaoImpl extends BaseDaoImpl<CmsAuthoritiy, Long>
		implements CmsAuthoritiyDao {

}
