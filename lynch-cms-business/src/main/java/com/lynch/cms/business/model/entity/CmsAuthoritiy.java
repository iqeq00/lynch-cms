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
@Table(name = "cms_authoritiy")
//@AttributeOverride(name = "id", column = @Column(name = "authoritiyId"))
// @JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class CmsAuthoritiy implements Serializable {

	private Long authoritiyId;
	private String authoritiyName;
	private String authoritiyDesc;
	private Date authoritiyCreateTime;
	private Date authoritiyUpadteTime;
	private Boolean enabled;
	private Set<CmsAuthoritiyResource> cmsAuthoritiyResources = new HashSet<CmsAuthoritiyResource>(0);
	private Set<CmsRoleAuthoritiy> cmsRoleAuthorities = new HashSet<CmsRoleAuthoritiy>(0);

	public CmsAuthoritiy() {
	}

	public CmsAuthoritiy(Long authoritiyId, String authoritiyName,
			String authoritiyDesc, Date authoritiyCreateTime,
			Date authoritiyUpadteTime, Boolean enabled) {
		this.authoritiyId = authoritiyId;
		this.authoritiyName = authoritiyName;
		this.authoritiyDesc = authoritiyDesc;
		this.authoritiyCreateTime = authoritiyCreateTime;
		this.authoritiyUpadteTime = authoritiyUpadteTime;
		this.enabled = enabled;
	}

	public CmsAuthoritiy(Long authoritiyId, String authoritiyName,
			String authoritiyDesc, Date authoritiyCreateTime,
			Date authoritiyUpadteTime, Boolean enabled,
			Set<CmsAuthoritiyResource> cmsAuthoritiyResources,
			Set<CmsRoleAuthoritiy> cmsRoleAuthorities) {
		this.authoritiyId = authoritiyId;
		this.authoritiyName = authoritiyName;
		this.authoritiyDesc = authoritiyDesc;
		this.authoritiyCreateTime = authoritiyCreateTime;
		this.authoritiyUpadteTime = authoritiyUpadteTime;
		this.enabled = enabled;
		this.cmsAuthoritiyResources = cmsAuthoritiyResources;
		this.cmsRoleAuthorities = cmsRoleAuthorities;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAuthoritiyId() {
		return authoritiyId;
	}

	public void setAuthoritiyId(Long authoritiyId) {
		this.authoritiyId = authoritiyId;
	}

	public String getAuthoritiyName() {
		return authoritiyName;
	}

	public void setAuthoritiyName(String authoritiyName) {
		this.authoritiyName = authoritiyName;
	}

	public String getAuthoritiyDesc() {
		return authoritiyDesc;
	}

	public void setAuthoritiyDesc(String authoritiyDesc) {
		this.authoritiyDesc = authoritiyDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAuthoritiyCreateTime() {
		return authoritiyCreateTime;
	}

	public void setAuthoritiyCreateTime(Date authoritiyCreateTime) {
		this.authoritiyCreateTime = authoritiyCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAuthoritiyUpadteTime() {
		return authoritiyUpadteTime;
	}

	public void setAuthoritiyUpadteTime(Date authoritiyUpadteTime) {
		this.authoritiyUpadteTime = authoritiyUpadteTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(mappedBy = "cmsAuthoritiy", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JsonIgnore
	public Set<CmsAuthoritiyResource> getCmsAuthoritiyResources() {
		return cmsAuthoritiyResources;
	}

	public void setCmsAuthoritiyResources(
			Set<CmsAuthoritiyResource> cmsAuthoritiyResources) {
		this.cmsAuthoritiyResources = cmsAuthoritiyResources;
	}

	@OneToMany(mappedBy = "cmsAuthoritiy", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JsonIgnore
	public Set<CmsRoleAuthoritiy> getCmsRoleAuthorities() {
		return cmsRoleAuthorities;
	}

	public void setCmsRoleAuthorities(Set<CmsRoleAuthoritiy> cmsRoleAuthorities) {
		this.cmsRoleAuthorities = cmsRoleAuthorities;
	}

}
