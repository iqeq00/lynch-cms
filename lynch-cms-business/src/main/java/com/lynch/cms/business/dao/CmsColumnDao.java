package com.lynch.cms.business.dao;

import java.util.List;

import com.lynch.cms.business.model.entity.CmsColumn;
import com.lynch.cms.core.dao.BaseDao;

/**
 * 栏目业务处理DAO--Inf
 * 
 * @author Lynch
 */
public interface CmsColumnDao extends BaseDao<CmsColumn, Integer> {

	/**
	 * 查询所有的栏目信息 
	 */
	List<CmsColumn> getCmsColumnList(Integer parentColumnId);

}
