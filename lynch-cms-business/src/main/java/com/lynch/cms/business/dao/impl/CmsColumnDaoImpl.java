package com.lynch.cms.business.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsColumnDao;
import com.lynch.cms.business.model.entity.CmsColumn;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 栏目业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsColumnDao")
public class CmsColumnDaoImpl extends BaseDaoImpl<CmsColumn, Integer> implements
		CmsColumnDao {

	/**
	 * 查询所有的栏目信息 
	 */
	public List<CmsColumn> getCmsColumnList(Integer parentColumnId) {
		
		Session session = this.getSession();
		String hql = "FROM CmsColumn AS cc WHERE cc.parentColumnId = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, parentColumnId);
		List<CmsColumn> list = query.list();
		if(list!=null&&list.size()>0){
			return list;
		}else {
			return null;
		}
	}

}
