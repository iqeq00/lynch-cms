package com.lynch.cms.business.dao;

import java.util.List;

import com.lynch.cms.business.model.entity.CmsAuthoritiyResource;
import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.core.dao.BaseDao;


/**
 * 权限资源业务处理DAO--Inf
 * 
 * @author Lynch
 */
public interface CmsAuthoritiyResourceDao extends
		BaseDao<CmsAuthoritiyResource, Long> {

	/**
	 * 根据权限名称来查询此权限可访问的资源 
	 */
	List<String> getCmsResourceString(String authoritiyName);
	
	/**
	 * 根据权限名称来查询此权限可访问的资源 
	 */
	List<CmsResource> getCmsResource(String authoritiyName);
	
	/**
	 * 根据资源的地址来查询此资源可被哪些权限访问 
	 */
	List<String> getAuthoritiy(String url);

	/**
	 * 得到所指定权限id的所有资源 
	 */
	List<CmsAuthoritiyResource> getCmsAuthoritiyResourceList(Long authoritiyId);
	
	/**
	 * 删除掉中间表所有此权限的所有的数据
	 */
	void deleteAuthoritiyResource(Long authoritiyId);

}
