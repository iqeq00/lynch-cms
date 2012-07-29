package com.lynch.cms.business.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsRoleAuthoritiyDao;
import com.lynch.cms.business.model.entity.CmsRoleAuthoritiy;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 角色权限业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsRoleAuthoritiyDao")
public class CmsRoleAuthoritiyDaoImpl extends
		BaseDaoImpl<CmsRoleAuthoritiy, Long> implements
		CmsRoleAuthoritiyDao {
	
	/**
	 * 根据角色id查询所有此角色id的权限
	 */
	public List<CmsRoleAuthoritiy> getRoleAuthoritiyList(Long roleId) {
		
		Session session = this.getSession();
		String hql = "FROM CmsRoleAuthoritiy AS cra WHERE cra.cmsRole.roleId = ? ";
		Query query = session.createQuery(hql);
		query.setLong(0, roleId);
		List<CmsRoleAuthoritiy> list = query.list();
		if(list!=null&&list.size()>0){
			return list;
		}else {
			return null;
		}
	}

	/**
	 * 删除掉中间表所有此角色的权限的数据
	 */
	public void deleteRoleAuthoritiy(Long roleId) {
		
		Session session = this.getSession();
		String hql = "DELETE CmsRoleAuthoritiy AS cra WHERE cra.cmsRole.roleId = ? ";
		Query query = session.createQuery(hql);
		query.setLong(0, roleId);
		query.executeUpdate();
	}

}
