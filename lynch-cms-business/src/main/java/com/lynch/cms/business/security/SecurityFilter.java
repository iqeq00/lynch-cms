package com.lynch.cms.business.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 通过Ioc生成securityMetadataSource，供Spring Security使用，用于权限校验。
 * 
 * @author lynch
 */
public class SecurityFilter extends AbstractSecurityInterceptor implements
		Filter {

	/**
	 * 一共三个组件，其他的两个组件，已经在AbstractSecurityInterceptor定义，这个组件是我们自己扩展的。
	 */
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	private void invoke(FilterInvocation fi) throws IOException,
			ServletException {

		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		
		return this.securityMetadataSource;
	}
	
	/**
	 * 下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误 
	 */
	@Override
	public Class<? extends Object> getSecureObjectClass() {
		
		return FilterInvocation.class;
	}

	public void init(FilterConfig filterconfig) throws ServletException {
	}

	public void destroy() {
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}
