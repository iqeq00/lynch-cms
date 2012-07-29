package com.lynch.cms.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAuthoritiyDao;
import com.lynch.cms.business.dao.CmsAuthoritiyResourceDao;
import com.lynch.cms.business.dao.CmsResourceDao;
import com.lynch.cms.business.dao.CmsRoleAuthoritiyDao;
import com.lynch.cms.business.model.entity.CmsAuthoritiy;
import com.lynch.cms.business.model.entity.CmsAuthoritiyResource;
import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.business.model.entity.CmsRoleAuthoritiy;
import com.lynch.cms.business.service.CmsAuthoritiySev;
import com.lynch.cms.core.model.vo.Pager;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 权限业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsAuthoritiySev")
public class CmsAuthoritiySevImpl extends BaseServiceImpl<CmsAuthoritiy, Long>
		implements CmsAuthoritiySev {

	private CmsAuthoritiyDao cmsAuthoritiyDao;
	private CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao;
	private CmsResourceDao cmsResourceDao;
	private CmsRoleAuthoritiyDao cmsRoleAuthoritiyDao;

	public Map<String, Object> pagerListTest(Integer rows, Integer page) {

		Pager<CmsAuthoritiy> pager = this.pagerList(rows, page);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("total", pager.getAllRow());
		result.put("rows", pager.getList());
		return result;
	}

	public void updateAuthoritiyResource(Long authoritiyId,
			List<Long> resourceIds) {

		// 删除掉中间表所有此权限的所有的数据
		cmsAuthoritiyResourceDao.deleteAuthoritiyResource(authoritiyId);
		// 得到权限对象
		CmsAuthoritiy cmsAuthoritiy = cmsAuthoritiyDao.get(authoritiyId);
		// 存放权限资源的set
		Set<CmsAuthoritiyResource> cmsAuthoritiyResources = new HashSet<CmsAuthoritiyResource>();
		// 循环将创建所有的权限资源信息
		Iterator<Long> iterator = resourceIds.iterator();
		while (iterator.hasNext()) {
			Long resourceId = (Long) iterator.next();
			CmsResource cmsResource = cmsResourceDao.get(resourceId);
			CmsAuthoritiyResource cmsAuthoritiyResource = new CmsAuthoritiyResource();
			cmsAuthoritiyResource.setCmsAuthoritiy(cmsAuthoritiy);
			cmsAuthoritiyResource.setCmsResource(cmsResource);
			cmsAuthoritiyResource.setAuthoritiyResourceCreateTime(new Date());
			cmsAuthoritiyResource.setEnabled(true);
			cmsAuthoritiyResources.add(cmsAuthoritiyResource);

		}
		cmsAuthoritiy.setCmsAuthoritiyResources(cmsAuthoritiyResources);
		cmsAuthoritiyDao.update(cmsAuthoritiy);
	}

	/**
	 * 得到权限信息
	 */
	public Map<String, Object> getAuthoritiyList(Long roleId) {

		Map<String, Object> result = new HashMap<String, Object>(2);
		List<CmsAuthoritiy> authoritiyList = cmsAuthoritiyDao.loadAll();
		List<CmsRoleAuthoritiy> roleAuthoritiyList = cmsRoleAuthoritiyDao
				.getRoleAuthoritiyList(roleId);
		for (int i = 0; i < authoritiyList.size(); i++) {
			CmsAuthoritiy authoritiy = authoritiyList.get(i);
			if (roleAuthoritiyList != null && roleAuthoritiyList.size() > 0) {
				for (int j = 0; j < roleAuthoritiyList.size(); j++) {
					CmsRoleAuthoritiy roleAuthoritiy = roleAuthoritiyList.get(j);
					if (authoritiy.getAuthoritiyId() == roleAuthoritiy.getCmsAuthoritiy().getAuthoritiyId()) {
						authoritiy.setEnabled(true);
						break;
					} else {
						authoritiy.setEnabled(false);
					}
				}
			} else {
				authoritiy.setEnabled(false);
			}
		}
		result.put("rows", authoritiyList);
		return result;
	}

	public CmsAuthoritiyDao getCmsAuthoritiyDao() {
		return cmsAuthoritiyDao;
	}

	@Resource
	public void setCmsAuthoritiyDao(CmsAuthoritiyDao cmsAuthoritiyDao) {
		super.setBaseDao(cmsAuthoritiyDao);
		this.cmsAuthoritiyDao = cmsAuthoritiyDao;
	}

	public CmsAuthoritiyResourceDao getCmsAuthoritiyResourceDao() {
		return cmsAuthoritiyResourceDao;
	}

	@Resource
	public void setCmsAuthoritiyResourceDao(
			CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao) {
		this.cmsAuthoritiyResourceDao = cmsAuthoritiyResourceDao;
	}

	public CmsResourceDao getCmsResourceDao() {
		return cmsResourceDao;
	}

	@Resource
	public void setCmsResourceDao(CmsResourceDao cmsResourceDao) {
		this.cmsResourceDao = cmsResourceDao;
	}

	public CmsRoleAuthoritiyDao getCmsRoleAuthoritiyDao() {
		return cmsRoleAuthoritiyDao;
	}

	@Resource
	public void setCmsRoleAuthoritiyDao(
			CmsRoleAuthoritiyDao cmsRoleAuthoritiyDao) {
		this.cmsRoleAuthoritiyDao = cmsRoleAuthoritiyDao;
	}

}
