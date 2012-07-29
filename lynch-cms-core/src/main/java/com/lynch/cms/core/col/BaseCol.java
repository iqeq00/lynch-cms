package com.lynch.cms.core.col;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class BaseCol implements ServletContextAware  {

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
