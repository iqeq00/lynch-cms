package com.lynch.cms.business.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsAdminRoleDao;
import com.lynch.cms.business.model.entity.CmsAdminRole;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 管理员角色业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsAdminRoleDao")
public class CmsAdminRoleDaoImpl extends BaseDaoImpl<CmsAdminRole, Long>
		implements CmsAdminRoleDao {

	/**
	 * 根据用户id来得到中间表的用户角色列表 
	 */
	public List<CmsAdminRole> getAdminRoleList(Long adminId) {
		
		Session session = this.getSession();
		String hql = "FROM CmsAdminRole AS car WHERE car.cmsAdmin.adminId = ? ";
		Query query = session.createQuery(hql);
		query.setLong(0, adminId);
		List<CmsAdminRole> list = query.list();
		if(list!=null&&list.size()>0){
			return list;
		}else {
			return null;
		}
	}

	/**
	 * 删除掉中间表所有此用户的所有的数据 
	 */
	public void deleteAdminRole(Long adminId) {

		Session session = this.getSession();
		String hql = "DELETE CmsAdminRole AS car WHERE car.cmsAdmin.adminId = ? ";
		Query query = session.createQuery(hql);
		query.setLong(0, adminId);
		query.executeUpdate();
	}

}
