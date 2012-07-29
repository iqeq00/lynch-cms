package com.lynch.cms.core.web.springmvc;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 初始化spring MVC数据绑定
 * 
 * @author karka.w
 */
public class BindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DateTypeEditor());
		binder.registerCustomEditor(int.class, new IntTypeSupport());
	}
}