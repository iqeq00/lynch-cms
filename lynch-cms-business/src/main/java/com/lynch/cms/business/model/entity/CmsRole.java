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
@Table(name = "cms_role")
//@AttributeOverride(name = "id", column = @Column(name = "roleId"))
public class CmsRole implements Serializable {

	private Long roleId;
	private String roleName;
	private String roleDesc;
	private Date roleCreateTime;
	private Date roleUpadteTime;
	private Boolean enabled;
	private Set<CmsAdminRole> adminRoles = new HashSet<CmsAdminRole>();
	private Set<CmsRoleAuthoritiy> roleAuthorities = new HashSet<CmsRoleAuthoritiy>();

	public CmsRole() {
	}

	public CmsRole(Long roleId, String roleName, String roleDesc,
			Date roleCreateTime, Date roleUpadteTime, Boolean enabled) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleCreateTime = roleCreateTime;
		this.roleUpadteTime = roleUpadteTime;
		this.enabled = enabled;
	}

	public CmsRole(Long roleId, String roleName, String roleDesc,
			Date roleCreateTime, Date roleUpadteTime, Boolean enabled,
			Set<CmsAdminRole> adminRoles, Set<CmsRoleAuthoritiy> roleAuthorities) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleCreateTime = roleCreateTime;
		this.roleUpadteTime = roleUpadteTime;
		this.enabled = enabled;
		this.adminRoles = adminRoles;
		this.roleAuthorities = roleAuthorities;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getRoleCreateTime() {
		return roleCreateTime;
	}

	public void setRoleCreateTime(Date roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getRoleUpadteTime() {
		return roleUpadteTime;
	}

	public void setRoleUpadteTime(Date roleUpadteTime) {
		this.roleUpadteTime = roleUpadteTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(mappedBy = "cmsRole", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JsonIgnore
	public Set<CmsAdminRole> getAdminRoles() {
		return adminRoles;
	}

	public void setAdminRoles(Set<CmsAdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}

	@OneToMany(mappedBy = "cmsRole", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JsonIgnore
	public Set<CmsRoleAuthoritiy> getRoleAuthorities() {
		return roleAuthorities;
	}

	public void setRoleAuthorities(Set<CmsRoleAuthoritiy> roleAuthorities) {
		this.roleAuthorities = roleAuthorities;
	}

}
