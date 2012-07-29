package com.lynch.cms.business.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;

import com.lynch.cms.business.dao.CmsAuthoritiyResourceDao;
import com.lynch.cms.business.dao.CmsResourceDao;
import com.lynch.cms.business.model.entity.CmsResource;

/**
 * 资源与权限建立关系，在服务器启动时就加载所有访问URL所需的权限，存入resourceMap集合中。
 * Spring在设置完一个bean所有的合作者后，会检查bean是否实现了InitializingBean接口，
 * 如果实现就调用bean的afterPropertiesSet方法，但这样便造成bean和spring的耦合。
 * 
 * @author lynch
 */
@Service("securityMetadataSource")
public class SecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private CmsResourceDao cmsResourceDao;
	private CmsAuthoritiyResourceDao authoritiyResourceDao;
	private RequestMatcher requestMatcher = null;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/**
	 * 加载所有资源与权限的关系，并组装成1个Map类型数据
	 */
	@PostConstruct
	public void init() {

		if (resourceMap == null) {
			resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
			// 查询出所有资源
			List<CmsResource> resources = cmsResourceDao.getResourcesByPriority();
			if(resources!=null&&resources.size()>0){
				//循环所有资源
				for (CmsResource cmsResource : resources) {
					Collection<ConfigAttribute> configAttributes = null;
					if (resourceMap.containsKey(cmsResource.getResourceString())) {
						configAttributes = resourceMap.get(cmsResource.getResourceString());
					} else {
						configAttributes = new ArrayList<ConfigAttribute>();
					}
					// 查询出某个资源可以被哪些权限操作
					List<String> authoritiyNames = authoritiyResourceDao.getAuthoritiy(cmsResource.getResourceString());
					if(authoritiyNames!=null&&authoritiyNames.size()>0){
						for (String authoritiyName : authoritiyNames) {
							ConfigAttribute configAttribute = new SecurityConfig(authoritiyName);
							configAttributes.add(configAttribute);
						}
					}
					resourceMap.put("/"+cmsResource.getResourceString(), configAttributes);
				}
			}
		}
//		System.out.println("111");
	}

	/**
	 * 返回所请求资源所需要的权限
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		if (resourceMap == null) {
			init();
		}
		FilterInvocation filterInvocation = (FilterInvocation) object;
		HttpServletRequest request = filterInvocation.getHttpRequest();
		// 循环判断请求的url是否对应map中过的url
		Iterator<String> iterator = resourceMap.keySet().iterator();
		while (iterator.hasNext()) {
			String resURL = iterator.next();
			requestMatcher = new AntPathRequestMatcher(resURL);
			if (requestMatcher.matches(request)) {
				// 如果匹配就返回权限的名称
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {

		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}
		return allAttributes;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public CmsResourceDao getCmsResourceDao() {
		return cmsResourceDao;
	}

	@Resource
	public void setCmsResourceDao(CmsResourceDao cmsResourceDao) {
		this.cmsResourceDao = cmsResourceDao;
	}

	public CmsAuthoritiyResourceDao getAuthoritiyResourceDao() {
		return authoritiyResourceDao;
	}

	@Resource
	public void setAuthoritiyResourceDao(
			CmsAuthoritiyResourceDao authoritiyResourceDao) {
		this.authoritiyResourceDao = authoritiyResourceDao;
	}

}
