package com.lynch.cms.business.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAdminDao;
import com.lynch.cms.business.model.entity.CmsAdmin;
import com.lynch.cms.business.model.entity.CmsAdminRole;
import com.lynch.cms.business.model.entity.CmsAuthoritiy;
import com.lynch.cms.business.model.entity.CmsRole;
import com.lynch.cms.business.model.entity.CmsRoleAuthoritiy;

/**
 * 查询出用户的基本信息和用户的权限信息，并封装成1个User对象
 * 
 * @author: lynch
 */
@Service("userDetailManager")
public class UserDetailManager implements UserDetailsService {

	private CmsAdminDao cmsAdminDao;

	/**
	 * 根据用户名取得其权限信息，并封装成user对象
	 */
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		CmsAdmin cmsAdmin = cmsAdminDao.getByName(userName);
		User user = null;
		if (cmsAdmin != null) {
			Set<CmsAuthoritiy> authorities = loadUserAuthorities(cmsAdmin);
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for (CmsAuthoritiy authoritiy : authorities) {
				SimpleGrantedAuthority gai = new SimpleGrantedAuthority(authoritiy.getAuthoritiyName());
				grantedAuthorities.add(gai);
			}
			user = new User(cmsAdmin.getAdminName(), cmsAdmin
					.getAdminPassword(), true, true, true, true,
					grantedAuthorities);

		} else {
			throw new UsernameNotFoundException("用户名“" + userName + "”不存在");
		}
		return user;

	}
	
	/**
	 * 根据用户得到用户的权限信息
	 * 
	 * @return 权限信息
	 */
	public Set<CmsAuthoritiy> loadUserAuthorities(CmsAdmin CmsAdmin) {

		try {
			Set<CmsAuthoritiy> authorities = new HashSet<CmsAuthoritiy>();
			if(CmsAdmin!=null){
				Set<CmsAdminRole> cmsAdminRoles = CmsAdmin.getAdminRoles();
				Iterator<CmsAdminRole> iterator = cmsAdminRoles.iterator();
				while (iterator.hasNext()) {
					CmsAdminRole cmsAdminRole = iterator.next();
					CmsRole role = cmsAdminRole.getCmsRole();
					Set<CmsRoleAuthoritiy> roleAuthorities = role.getRoleAuthorities();
					Iterator<CmsRoleAuthoritiy> iter = roleAuthorities.iterator();
					while (iter.hasNext()) {
						CmsRoleAuthoritiy cmsRoleAuthoritiy = iter.next();
						CmsAuthoritiy cmsAuthoritiy = cmsRoleAuthoritiy.getCmsAuthoritiy();
						authorities.add(cmsAuthoritiy);
					}
				}
			}
			return authorities;
		} catch (RuntimeException re) {
			return null;
		}
	}

	public CmsAdminDao getCmsAdminDao() {
		return cmsAdminDao;
	}

	@Resource
	public void setCmsAdminDao(CmsAdminDao cmsAdminDao) {
		this.cmsAdminDao = cmsAdminDao;
	}

}
