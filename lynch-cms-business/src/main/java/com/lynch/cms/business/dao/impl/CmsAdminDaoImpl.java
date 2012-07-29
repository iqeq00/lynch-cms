package com.lynch.cms.business.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsAdminDao;
import com.lynch.cms.business.model.entity.CmsAdmin;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 管理员业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsAdminDao")
public class CmsAdminDaoImpl extends BaseDaoImpl<CmsAdmin, Long> implements
		CmsAdminDao {

	/**
	 * 根据用户名字查询用户 
	 */
	public CmsAdmin getByName(String userName) {
		
		Session session = this.getSession();
		String hql = "FROM CmsAdmin AS ca WHERE ca.adminName = ?";
		Query query = session.createQuery(hql);
		query.setString(0, userName);
		List<CmsAdmin> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	
}
