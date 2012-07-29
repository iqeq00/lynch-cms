package com.lynch.cms.business.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lynch.cms.business.dao.CmsAuthoritiyResourceDao;
import com.lynch.cms.business.model.entity.CmsAuthoritiyResource;
import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.core.dao.impl.BaseDaoImpl;

/**
 * 权限业务处理DAO--Impl
 * 
 * @author Lynch
 */
@Repository("cmsAuthoritiyResourceDao")
public class CmsAuthoritiyResourceDaoImpl extends
		BaseDaoImpl<CmsAuthoritiyResource, Long> implements
		CmsAuthoritiyResourceDao {

	/**
	 * 根据权限名称来查询此权限可访问的资源 
	 */
	public List<String> getCmsResourceString(String authoritiyName) {
		
		Session session = this.getSession();
		String hql = "FROM CmsAuthoritiyResource AS car WHERE car.cmsAuthoritiy.authoritiyName = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, authoritiyName);
		List<CmsAuthoritiyResource> list = query.list();
		List<String> resourceStringList = new ArrayList<String>();
		Iterator<CmsAuthoritiyResource> iterator = list.iterator();
		while (iterator.hasNext()) {
			CmsAuthoritiyResource cmsAuthoritiyResource = iterator.next();
			resourceStringList.add(cmsAuthoritiyResource.getCmsResource().getResourceString());
		}
		return resourceStringList;
	}
	
	/**
	 * 根据权限名称来查询此权限可访问的资源 
	 */
	public List<CmsResource> getCmsResource(String authoritiyName) {
		
		Session session = this.getSession();
		String hql = "FROM CmsAuthoritiyResource AS car WHERE car.cmsAuthoritiy.authoritiyName = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, authoritiyName);
		List<CmsAuthoritiyResource> list = query.list();
		List<CmsResource> resourceList = new ArrayList<CmsResource>();
		Iterator<CmsAuthoritiyResource> iterator = list.iterator();
		while (iterator.hasNext()) {
			CmsAuthoritiyResource cmsAuthoritiyResource = iterator.next();
			resourceList.add(cmsAuthoritiyResource.getCmsResource());
		}
		return resourceList;
	}
	
	/**
	 * 根据资源的地址来查询此资源可被哪些权限访问 
	 */
	public List<String> getAuthoritiy(String url) {
		
		Session session = this.getSession();
		String hql = "FROM CmsAuthoritiyResource AS car WHERE car.cmsResource.resourceString = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, url);
		List<CmsAuthoritiyResource> list = query.list();
		List<String> authoritiyStringList = new ArrayList<String>();
		Iterator<CmsAuthoritiyResource> iterator = list.iterator();
		while (iterator.hasNext()) {
			CmsAuthoritiyResource cmsAuthoritiyResource = iterator.next();
			authoritiyStringList.add(cmsAuthoritiyResource.getCmsAuthoritiy().getAuthoritiyName());
		}
		return authoritiyStringList;
	}
	
	
	/**
	 * 得到所指定权限id的所有资源 
	 */
	public List<CmsAuthoritiyResource> getCmsAuthoritiyResourceList(
			Long authoritiyId) {
		
		Session session = this.getSession();
		String hql = "FROM CmsAuthoritiyResource AS car WHERE car.cmsAuthoritiy.authoritiyId = ? ";
		Query query = session.createQuery(hql);
		query.setLong(0, authoritiyId);
		List<CmsAuthoritiyResource> list = query.list();
		if(list!=null&&list.size()>0){
			return list;
		}else {
			return null;
		}
	}


	/**
	 * 删除掉中间表所有此权限的所有的数据
	 */
	public void deleteAuthoritiyResource(Long authoritiyId) {
		
		if(authoritiyId!=null){
			Session session = this.getSession();
			String hql = "DELETE CmsAuthoritiyResource AS car WHERE car.cmsAuthoritiy.authoritiyId = ? ";
			Query query = session.createQuery(hql);
			query.setLong(0, authoritiyId);
			query.executeUpdate();
		}
		
	}

}
