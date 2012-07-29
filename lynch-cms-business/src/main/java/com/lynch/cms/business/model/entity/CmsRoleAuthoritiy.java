package com.lynch.cms.business.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lynch.cms.core.web.springmvc.JackDateSerializer;

@Entity
@Table(name = "cms_role_authoritiy")
//@AttributeOverride(name = "id", column = @Column(name = "roleAuthoritiyId"))
// @JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class CmsRoleAuthoritiy implements Serializable {

	private Long roleAuthoritiyId;
	private Date roleAuthoritiyCreateTime;
	private Date roleAuthoritiyUpadteTime;
	private Boolean enabled;
	private CmsRole cmsRole;
	private CmsAuthoritiy cmsAuthoritiy;

	public CmsRoleAuthoritiy() {
	}

	public CmsRoleAuthoritiy(Long roleAuthoritiyId,
			Date roleAuthoritiyCreateTime, Date roleAuthoritiyUpadteTime,
			Boolean enabled, CmsRole cmsRole, CmsAuthoritiy cmsAuthoritiy) {
		this.roleAuthoritiyId = roleAuthoritiyId;
		this.roleAuthoritiyCreateTime = roleAuthoritiyCreateTime;
		this.roleAuthoritiyUpadteTime = roleAuthoritiyUpadteTime;
		this.enabled = enabled;
		this.cmsRole = cmsRole;
		this.cmsAuthoritiy = cmsAuthoritiy;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getRoleAuthoritiyId() {
		return roleAuthoritiyId;
	}

	public void setRoleAuthoritiyId(Long roleAuthoritiyId) {
		this.roleAuthoritiyId = roleAuthoritiyId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getRoleAuthoritiyCreateTime() {
		return roleAuthoritiyCreateTime;
	}

	public void setRoleAuthoritiyCreateTime(Date roleAuthoritiyCreateTime) {
		this.roleAuthoritiyCreateTime = roleAuthoritiyCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getRoleAuthoritiyUpadteTime() {
		return roleAuthoritiyUpadteTime;
	}

	public void setRoleAuthoritiyUpadteTime(Date roleAuthoritiyUpadteTime) {
		this.roleAuthoritiyUpadteTime = roleAuthoritiyUpadteTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public CmsRole getCmsRole() {
		return cmsRole;
	}

	public void setCmsRole(CmsRole cmsRole) {
		this.cmsRole = cmsRole;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public CmsAuthoritiy getCmsAuthoritiy() {
		return cmsAuthoritiy;
	}

	public void setCmsAuthoritiy(CmsAuthoritiy cmsAuthoritiy) {
		this.cmsAuthoritiy = cmsAuthoritiy;
	}

}
