package com.lynch.cms.business.service;

import java.util.List;
import java.util.Map;

import com.lynch.cms.business.model.entity.CmsAdmin;
import com.lynch.cms.business.model.vo.NodeTree;
import com.lynch.cms.core.service.BaseService;

/**
 * 管理员业务处理Sev--Inf
 * 
 * @author Lynch
 */
public interface CmsAdminSev extends BaseService<CmsAdmin, Long> {

	Map<String, Object> pagerListTest(int dATANUMBER, Integer page,
			CmsAdmin cmsAdmin);

	/**
	 * 设置用户的角色信息
	 * 
	 * @return map 包含1个key-value(操作是否成功)
	 */
	void updateAdminRole(Long adminId, List<Long> roleIds);

	/**
	 * 获取用户栏目树 
	 * 
	 * @return list 1个 目录树
	 */
	List<NodeTree> getAsyncTree();
}
