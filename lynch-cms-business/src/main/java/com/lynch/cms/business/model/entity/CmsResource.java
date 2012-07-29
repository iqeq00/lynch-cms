package com.lynch.cms.business.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lynch.cms.core.web.springmvc.JackDateSerializer;

@Entity
@Table(name="cms_resource")
//@AttributeOverride(name = "id", column = @Column(name = "resourceId"))
public class CmsResource implements Serializable {

	private Long resourceId;
	private String resourceName;
	private String resourceType;
	private String resourceString;
	private String resourceDesc;
	private Integer resourcePriority;
	private Date resourceCreateTime;
	private Date resourceUpadteTime;
	private Boolean enabled;
//	private Boolean isDirectory;
	private Integer columnId;
	private Set<CmsAuthoritiyResource> authoritiyResources = new HashSet<CmsAuthoritiyResource>();
	
	public CmsResource() {
	}

	public CmsResource(Long resourceId, String resourceName,
			String resourceType, String resourceString, String resourceDesc,
			Integer resourcePriority, Date resourceCreateTime,
			Date resourceUpadteTime, Boolean enabled, Integer columnId) {
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.resourceString = resourceString;
		this.resourceDesc = resourceDesc;
		this.resourcePriority = resourcePriority;
		this.resourceCreateTime = resourceCreateTime;
		this.resourceUpadteTime = resourceUpadteTime;
		this.enabled = enabled;
		this.columnId = columnId;
	}

	public CmsResource(Long resourceId, String resourceName,
			String resourceType, String resourceString, String resourceDesc,
			Integer resourcePriority, Date resourceCreateTime,
			Date resourceUpadteTime, Boolean enabled, Integer columnId,
			Set<CmsAuthoritiyResource> authoritiyResources) {
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.resourceString = resourceString;
		this.resourceDesc = resourceDesc;
		this.resourcePriority = resourcePriority;
		this.resourceCreateTime = resourceCreateTime;
		this.resourceUpadteTime = resourceUpadteTime;
		this.enabled = enabled;
		this.columnId = columnId;
		this.authoritiyResources = authoritiyResources;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceString() {
		return resourceString;
	}

	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public Integer getResourcePriority() {
		return resourcePriority;
	}

	public void setResourcePriority(Integer resourcePriority) {
		this.resourcePriority = resourcePriority;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getResourceCreateTime() {
		return resourceCreateTime;
	}

	public void setResourceCreateTime(Date resourceCreateTime) {
		this.resourceCreateTime = resourceCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getResourceUpadteTime() {
		return resourceUpadteTime;
	}

	public void setResourceUpadteTime(Date resourceUpadteTime) {
		this.resourceUpadteTime = resourceUpadteTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
//	public Boolean getIsDirectory() {
//		return isDirectory;
//	}
//
//	public void setIsDirectory(Boolean isDirectory) {
//		this.isDirectory = isDirectory;
//	}
//
//	public Long getDirectoryId() {
//		return directoryId;
//	}
//
//	public void setDirectoryId(Long directoryId) {
//		this.directoryId = directoryId;
//	}
	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}


	@OneToMany(mappedBy="cmsResource",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JsonIgnore
	public Set<CmsAuthoritiyResource> getAuthoritiyResources() {
		return authoritiyResources;
	}

	public void setAuthoritiyResources(
			Set<CmsAuthoritiyResource> authoritiyResources) {
		this.authoritiyResources = authoritiyResources;
	}
	
}
