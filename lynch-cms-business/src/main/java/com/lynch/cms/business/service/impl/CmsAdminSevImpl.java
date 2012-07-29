package com.lynch.cms.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.mapping.Array;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAdminDao;
import com.lynch.cms.business.dao.CmsAdminRoleDao;
import com.lynch.cms.business.dao.CmsAuthoritiyResourceDao;
import com.lynch.cms.business.dao.CmsColumnDao;
import com.lynch.cms.business.dao.CmsRoleDao;
import com.lynch.cms.business.model.entity.CmsAdmin;
import com.lynch.cms.business.model.entity.CmsAdminRole;
import com.lynch.cms.business.model.entity.CmsColumn;
import com.lynch.cms.business.model.entity.CmsResource;
import com.lynch.cms.business.model.entity.CmsRole;
import com.lynch.cms.business.model.vo.NodeTree;
import com.lynch.cms.business.service.CmsAdminSev;
import com.lynch.cms.core.model.vo.Pager;
import com.lynch.cms.core.security.springsecurity.SpringSecurityUtils;
import com.lynch.cms.core.service.impl.BaseServiceImpl;

/**
 * 管理员业务处理Sev--Impl
 * 
 * @author Lynch
 */
@Service("cmsAdminSev")
public class CmsAdminSevImpl extends BaseServiceImpl<CmsAdmin, Long> implements
		CmsAdminSev {

	private CmsAdminDao cmsAdminDao;
	private CmsAdminRoleDao cmsAdminRoleDao;
	private CmsRoleDao cmsRoleDao;
	private CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao;
	private CmsColumnDao cmsColumnDao;

	public Map<String, Object> pagerListTest(int dATANUMBER, Integer page,
			CmsAdmin cmsAdmin) {

		String hql = "FROM CmsAdmin AS ca WHERE 1=1 ";
		Map<String, Object> param = new HashMap<String, Object>();
		if (cmsAdmin.getAdminName() != null
				&& !"".equals(cmsAdmin.getAdminName())) {
			hql = hql + " AND ca.adminName like:a";
			param.put("a", '%' + cmsAdmin.getAdminName() + '%');
		}
		// if(cmsAdmin.getAdminAge()!=null&&cmsAdmin.getAdminAge()>0){
		// hql = hql + " AND ca.adminAge = :b";
		// param.put("b",cmsAdmin.getAdminAge());
		// }
		// if(cmsAdmin.getCreateTime()!=null&&!"".equals(cmsAdmin.getCreateTime())){
		// hql = hql + " AND ca.createTime > :c AND ca.createTime <:d";
		// param.put("c", cmsAdmin.getCreateTime());
		// param.put("d", DateUtil.TomorrowToDate(cmsAdmin.getCreateTime()));
		// }
		// System.out.println("hql=" + hql);
		Pager<CmsAdmin> pager = this.pagerList(dATANUMBER, page, hql, param);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("total", pager.getAllRow());
		result.put("rows", pager.getList());
		return result;
	}

	public void updateAdminRole(Long adminId, List<Long> roleIds) {

		// 删除掉中间表所有此用户的所有的数据
		cmsAdminRoleDao.deleteAdminRole(adminId);
		// 得到用户对象
		CmsAdmin cmsAdmin = cmsAdminDao.get(adminId);
		// 存放角色资源的set
		Set<CmsAdminRole> cmsAdminRoles = new HashSet<CmsAdminRole>();
		// 循环将创建所有的用户角色信息
		// for(int i=0;i<roleIdStr.length;i++){
		// Role role = roleDao.get(Long.parseLong(roleIdStr[i]));
		// UserRole userRole = new UserRole();
		// userRole.setRole(role);
		// userRole.setUser(user);
		// userRole.setEnabled(true);
		// userRoles.add(userRole);
		// }
		Iterator<Long> iterator = roleIds.iterator();
		while (iterator.hasNext()) {
			Long roleId = iterator.next();
			CmsRole cmsRole = cmsRoleDao.get(roleId);
			CmsAdminRole cmsAdminRole = new CmsAdminRole();
			cmsAdminRole.setCmsAdmin(cmsAdmin);
			cmsAdminRole.setCmsRole(cmsRole);
			cmsAdminRole.setAdminRoleCreateTime(new Date());
			cmsAdminRole.setEnabled(true);
			cmsAdminRoles.add(cmsAdminRole);
		}
		// user.setUserRoles(userRoles);
		cmsAdmin.setAdminRoles(cmsAdminRoles);
		cmsAdminDao.update(cmsAdmin);
		// userDao.update(user);
	}
	
	/**
	 * 获取用户栏目树 
	 * 
	 * @return list 1个 目录树
	 */
	public List<NodeTree> getAsyncTree() {
		
		//根据权限取得所有的资源
		Set<CmsResource> resources = new HashSet<CmsResource>();
		User user = SpringSecurityUtils.getCurrentUser();
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		while (iterator.hasNext()) {
			GrantedAuthority grantedAuthority = iterator.next();
			String authoritiyName = grantedAuthority.getAuthority();
			List<CmsResource> list = cmsAuthoritiyResourceDao.getCmsResource(authoritiyName);
			resources.addAll(list);
		}
		//查出所有的栏目
		List<CmsColumn> columnList = cmsColumnDao.loadAll();
		List<NodeTree> nodeTreeList = new ArrayList<NodeTree>();
		//循环资源创建新的栏目
//		Iterator<CmsResource> it = resources.iterator();
//		while (it.hasNext()) {
//			CmsResource cmsResource = it.next();
//			Iterator<CmsColumn> iter = columnList.iterator();
//			while (iter.hasNext()) {
//				CmsColumn cmsColumn = iter.next();
//				if(cmsResource.getColumnId() == cmsColumn.getColumnId()){
//					//创建栏目目录
//					NodeTree nodeTree = new NodeTree(cmsColumn.getColumnId(),cmsColumn.getColumnName(),new HashSet<NodeTree>(),new HashMap<String, Object>());
//					//创建资源按钮
//					NodeTree nodeTreeChildren = new NodeTree(cmsColumn.getColumnId(),cmsResource.getResourceDesc(),new HashSet<NodeTree>(),new HashMap<String, Object>());
//					//设置资源的路径
//					nodeTreeChildren.getAttributes().put("url", cmsResource.getResourceString());
//					//讲资源设置到栏目
//					nodeTree.getChildren().add(nodeTreeChildren);
//					nodeTreeList.add(nodeTree);
//				}
//			}
//		}
		//循环目录来创建资源
		Iterator<CmsColumn> iter = columnList.iterator();
		while (iter.hasNext()) {
			CmsColumn cmsColumn = iter.next();
			Iterator<CmsResource> it = resources.iterator();
			while (it.hasNext()) {
				CmsResource cmsResource = it.next();
				if(cmsColumn.getColumnId() == cmsResource.getColumnId()){
					NodeTree nodeTree = getNodeTree(nodeTreeList, cmsResource.getColumnId());
					if(nodeTree==null){
						//创建栏目目录
						nodeTree = new NodeTree(cmsColumn.getColumnId(),cmsColumn.getColumnName(),new HashSet<NodeTree>(),new HashMap<String, Object>());
						//创建资源按钮
						NodeTree nodeTreeChildren = new NodeTree(cmsColumn.getColumnId(),cmsResource.getResourceDesc(),new HashSet<NodeTree>(),new HashMap<String, Object>());
						//设置资源的路径
						nodeTreeChildren.getAttributes().put("url", cmsResource.getResourceString());
						//讲资源设置到栏目
						nodeTree.getChildren().add(nodeTreeChildren);
						nodeTreeList.add(nodeTree);
					}else{
						//创建资源按钮
						NodeTree nodeTreeChildren = new NodeTree(cmsColumn.getColumnId(),cmsResource.getResourceDesc(),new HashSet<NodeTree>(),new HashMap<String, Object>());
						//设置资源的路径
						nodeTreeChildren.getAttributes().put("url", cmsResource.getResourceString());
						//讲资源设置到栏目
						nodeTree.getChildren().add(nodeTreeChildren);
					}
				}
			}
		}
		return nodeTreeList;
	}
	
	/**
	 * 查看是否有此目录 
	 * 
	 * @return NodeTree
	 */
	public NodeTree getNodeTree(List<NodeTree> nodeTreeList, Integer cmsColumnId){
		
		Iterator<NodeTree> iterator = nodeTreeList.iterator();
		while (iterator.hasNext()) {
			NodeTree nodeTree = iterator.next();
			if(nodeTree.getId().intValue() == cmsColumnId.intValue()){
				return nodeTree;
			}
		}
		return null;
	}
	

	public CmsAdminDao getCmsAdminDao() {
		return cmsAdminDao;
	}

	@Resource
	public void setCmsAdminDao(CmsAdminDao cmsAdminDao) {
		super.setBaseDao(cmsAdminDao);
		this.cmsAdminDao = cmsAdminDao;
	}

	public CmsAdminRoleDao getCmsAdminRoleDao() {
		return cmsAdminRoleDao;
	}

	@Resource
	public void setCmsAdminRoleDao(CmsAdminRoleDao cmsAdminRoleDao) {
		this.cmsAdminRoleDao = cmsAdminRoleDao;
	}

	public CmsRoleDao getCmsRoleDao() {
		return cmsRoleDao;
	}

	@Resource
	public void setCmsRoleDao(CmsRoleDao cmsRoleDao) {
		this.cmsRoleDao = cmsRoleDao;
	}

	public CmsAuthoritiyResourceDao getCmsAuthoritiyResourceDao() {
		return cmsAuthoritiyResourceDao;
	}

	@Resource
	public void setCmsAuthoritiyResourceDao(
			CmsAuthoritiyResourceDao cmsAuthoritiyResourceDao) {
		this.cmsAuthoritiyResourceDao = cmsAuthoritiyResourceDao;
	}

	public CmsColumnDao getCmsColumnDao() {
		return cmsColumnDao;
	}

	@Resource
	public void setCmsColumnDao(CmsColumnDao cmsColumnDao) {
		this.cmsColumnDao = cmsColumnDao;
	}
	
	
	
	

}
