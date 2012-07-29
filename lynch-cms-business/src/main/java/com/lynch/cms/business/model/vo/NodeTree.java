package com.lynch.cms.business.model.vo;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Jquery Easyui Tree 扩展vo
 * 
 * @author Lynch
 */
public class NodeTree implements Serializable {

	private Integer id;
	private String text;
	private Set<NodeTree> children;
	private Map<String,Object> attributes;
	private NodeTree parent;
	
	public NodeTree() {
	}
	
	public NodeTree(Integer id, String text, Set<NodeTree> children,
			Map<String, Object> attributes) {
		this.id = id;
		this.text = text;
		this.children = children;
		this.attributes = attributes;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Set<NodeTree> getChildren() {
		return children;
	}
	public void setChildren(Set<NodeTree> children) {
		this.children = children;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
}
