package com.lynch.cms.core.web.springmvc;

import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;

/**
 * Integer类型转换器
 * 
 * @author Lynch
 * @version 1.0
 */
public class IntTypeSupport extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {

		if (text == null || text.equals(""))
			text = "0";
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Integer.parseInt(text));
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		return getValue().toString();
	}
}
