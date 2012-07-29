package com.lynch.cms.business.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsResourceDao;
import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 资源业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsResourceDao")
public class CmsResourceDaoImpl extends BaseDaoImpl<CmsResource, Long>
		implements CmsResourceDao {

	/**
	 * 查询所有的资源，并根据优先级排序 
	 */
	public List<CmsResource> getResourcesByPriority() {
		
		Session session = this.getSession();
		String hql = "FROM CmsResource AS cr ORDER BY cr.resourcePriority DESC";
		Query query = session.createQuery(hql);
		List<CmsResource> list = query.list();
		if(list!=null&&list.size()>0){
			return list;
		}else {
			return null;
		}
	}

}
