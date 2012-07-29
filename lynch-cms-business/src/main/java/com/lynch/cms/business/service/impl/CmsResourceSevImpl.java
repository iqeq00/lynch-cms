package com.lynch.cms.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAuthoritiyResourceDao;
import com.lynch.cms.business.dao.CmsResourceDao;
import com.lynch.cms.business.model.entity.CmsAuthoritiyResource;
import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.business.service.CmsResourceSev;
import com.lynch.cms.core.model.vo.Pager;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 资源业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsResourceSev")
public class CmsResourceSevImpl extends BaseServiceImpl<CmsResource, Long>
		implements CmsResourceSev {

	private CmsResourceDao cmsResourceDao;
	private CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao;

	public Map<String, Object> pagerListTest(Integer rows, Integer page,
			CmsResource cmsResource) {

		Pager<CmsResource> pager = this.pagerList(rows, page);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("total", pager.getAllRow());
		result.put("rows", pager.getList());
		return result;
	}

	/**
	 * 得到资源信息
	 */
	public Map<String, Object> getResourceList(Long authoritiyId) {

		Map<String, Object> result = new HashMap<String, Object>(2);

		List<CmsResource> resourceList = cmsResourceDao.loadAll();
		List<CmsAuthoritiyResource> cmsAuthoritiyResourceList = cmsAuthoritiyResourceDao
				.getCmsAuthoritiyResourceList(authoritiyId);
		for (int i = 0; i < resourceList.size(); i++) {
			CmsResource cmsResource = resourceList.get(i);
			if (cmsAuthoritiyResourceList != null
					&& cmsAuthoritiyResourceList.size() > 0) {
				for (int j = 0; j < cmsAuthoritiyResourceList.size(); j++) {
					CmsAuthoritiyResource cmsAuthoritiyResource = cmsAuthoritiyResourceList.get(j);
					if (cmsResource.getResourceId() == cmsAuthoritiyResource
							.getCmsResource().getResourceId()) {
						cmsResource.setEnabled(true);
						break;
					} else {
						cmsResource.setEnabled(false);
					}
				}
			} else {
				cmsResource.setEnabled(false);
			}
		}

		result.put("rows", resourceList);
		return result;
	}

	public CmsResourceDao getCmsResourceDao() {
		return cmsResourceDao;
	}

	@Resource
	public void setCmsResourceDao(CmsResourceDao cmsResourceDao) {
		super.setBaseDao(cmsResourceDao);
		this.cmsResourceDao = cmsResourceDao;
	}

	public CmsAuthoritiyResourceDao getCmsAuthoritiyResourceDao() {
		return cmsAuthoritiyResourceDao;
	}

	@Resource
	public void setCmsAuthoritiyResourceDao(
			CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao) {
		this.cmsAuthoritiyResourceDao = cmsAuthoritiyResourceDao;
	}

}
