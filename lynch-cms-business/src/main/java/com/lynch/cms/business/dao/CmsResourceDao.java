package com.lynch.cms.business.dao;

import java.util.List;

import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.core.dao.BaseDao;


/**
 * 资源业务处理DAO--Inf
 * 
 * @author Lynch
 */
public interface CmsResourceDao extends BaseDao<CmsResource, Long> {

	/**
	 * 查询所有的资源，并根据优先级排序 
	 */
	List<CmsResource> getResourcesByPriority();

}
