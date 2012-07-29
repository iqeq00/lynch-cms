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
@Table(name = "cms_admin_role")
//@AttributeOverride(name = "id", column = @Column(name = "adminRoleId"))
public class CmsAdminRole implements Serializable {

	private Long adminRoleId;
	private Date adminRoleCreateTime;
	private Date adminRoleUpadteTime;
	private Boolean enabled;
	private CmsAdmin cmsAdmin;
	private CmsRole cmsRole;

	public CmsAdminRole() {
	}

	public CmsAdminRole(Long adminRoleId, Date adminRoleCreateTime,
			Date adminRoleUpadteTime, Boolean enabled, CmsAdmin cmsAdmin,
			CmsRole cmsRole) {
		this.adminRoleId = adminRoleId;
		this.adminRoleCreateTime = adminRoleCreateTime;
		this.adminRoleUpadteTime = adminRoleUpadteTime;
		this.enabled = enabled;
		this.cmsAdmin = cmsAdmin;
		this.cmsRole = cmsRole;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAdminRoleId() {
		return adminRoleId;
	}

	public void setAdminRoleId(Long adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAdminRoleCreateTime() {
		return adminRoleCreateTime;
	}

	public void setAdminRoleCreateTime(Date adminRoleCreateTime) {
		this.adminRoleCreateTime = adminRoleCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAdminRoleUpadteTime() {
		return adminRoleUpadteTime;
	}

	public void setAdminRoleUpadteTime(Date adminRoleUpadteTime) {
		this.adminRoleUpadteTime = adminRoleUpadteTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public CmsAdmin getCmsAdmin() {
		return cmsAdmin;
	}

	public void setCmsAdmin(CmsAdmin cmsAdmin) {
		this.cmsAdmin = cmsAdmin;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public CmsRole getCmsRole() {
		return cmsRole;
	}

	public void setCmsRole(CmsRole cmsRole) {
		this.cmsRole = cmsRole;
	}

}
