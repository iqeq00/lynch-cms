package com.lynch.cms.business.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * 
 * 所有的Authentication实现需要保存在一个GrantedAuthority对象数组中，这就是赋予给主体的权限。
 * GrantedAuthority对象通过AuthenticationManager 保存到Authentication对象里。
 * 然后从AccessDecisionManager读出来，进行授权判断。
 * 
 * Spring Security提供了一些拦截器，来控制对安全对象的访问权限，例如方法调用或web请求。
 * 一个是否允许执行调用的预调用决定，是由AccessDecisionManager实现的。 
 * 这个 AccessDecisionManager被AbstractSecurityInterceptor调用， 它用来作最终访问控制的决定。
 * 
 * @author lynch
 */
@Service("accessDecisionManager")
public class AccessDecisionManager implements
		org.springframework.security.access.AccessDecisionManager {

	/**
	 * authentication用户认证后 存有用户的所有权限 
	 * configAttributes访问所需要的权限 若无权则抛出异常
	 * 
	 * AccessDecisionManager使用方法参数传递所有信息，这好像在认证评估时进行决定。
	 * 特别是，在真实的安全方法期望调用的时候，传递安全Object启用那些参数。 
	 * 比如，让我们假设安全对象是一个MethodInvocation。
	 * 很容易为任何Customer参数查询MethodInvocation，
	 * 然后在AccessDecisionManager里实现一些有序的安全逻辑，来确认主体是否允许在那个客户上操作。
	 * 如果访问被拒绝，实现将抛出一个AccessDeniedException异常。
	 */
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (null == configAttributes) {
			return;
		}
		// 所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			String needPermission = configAttribute.getAttribute();
			// 用户所拥有的权限authentication
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (needPermission.equals(grantedAuthority.getAuthority())) {
					return;
				}
			}
		}
		// 无权限抛出拒绝异常
		throw new AccessDeniedException("");
	}

	/**
	 * 这个方法在启动的时候被AbstractSecurityInterceptor调用，
	 * 来决定AccessDecisionManager是否可以执行传递ConfigAttribute。
	 */
	public boolean supports(ConfigAttribute attribute) {

		return true;
	}

	/**
	 * 方法被安全拦截器实现调用， 包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型。
	 */
	public boolean supports(Class<?> clazz) {

		return true;
	}

}
