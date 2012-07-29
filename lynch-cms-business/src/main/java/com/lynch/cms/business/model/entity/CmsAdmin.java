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
@Table(name = "cms_admin")
//@AttributeOverride(name = "id", column = @Column(name = "adminId"))
public class CmsAdmin implements Serializable {

	private Long adminId;
	private String adminName;
	private String adminPassword;
	private Date adminCreateTime;
	private Date adminUpadteTime;
	private Boolean enabled;
	private Set<CmsAdminRole> adminRoles = new HashSet<CmsAdminRole>();

	public CmsAdmin() {

	}

	public CmsAdmin(Long adminId, String adminName, String adminPassword,
			Date adminCreateTime, Date adminUpadteTime, Boolean enabled) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminCreateTime = adminCreateTime;
		this.adminUpadteTime = adminUpadteTime;
		this.enabled = enabled;
	}

	public CmsAdmin(Long adminId, String adminName, String adminPassword,
			Date adminCreateTime, Date adminUpadteTime, Boolean enabled,
			Set<CmsAdminRole> adminRoles) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminCreateTime = adminCreateTime;
		this.adminUpadteTime = adminUpadteTime;
		this.enabled = enabled;
		this.adminRoles = adminRoles;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAdminCreateTime() {
		return adminCreateTime;
	}

	public void setAdminCreateTime(Date adminCreateTime) {
		this.adminCreateTime = adminCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAdminUpadteTime() {
		return adminUpadteTime;
	}

	public void setAdminUpadteTime(Date adminUpadteTime) {
		this.adminUpadteTime = adminUpadteTime;
	}

	@OneToMany(mappedBy = "cmsAdmin", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JsonIgnore
	//json处理的时候必须加上这个注解，相当于这个对象不参与json的转化，为什么呢，因为对象的关系，会导致死循环
	public Set<CmsAdminRole> getAdminRoles() {
		return adminRoles;
	}

	public void setAdminRoles(Set<CmsAdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}

}
