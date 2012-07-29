package com.lynch.cms.business.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cms_column")
public class CmsColumn implements Serializable {

	private Integer columnId;
	private String columnName;
	
	public CmsColumn() {
	}

	public CmsColumn(Integer columnId, String columnName) {
		this.columnId = columnId;
		this.columnName = columnName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getColumnName() {
		return columnName;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}
