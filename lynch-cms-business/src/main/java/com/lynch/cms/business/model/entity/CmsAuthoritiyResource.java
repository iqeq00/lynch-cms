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
@Table(name="cms_authoritiy_resource")
//@AttributeOverride(name = "id", column = @Column(name = "authoritiyResourceId"))
//@JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 
public class CmsAuthoritiyResource implements Serializable {

	private Long authoritiyResourceId;
	private Date authoritiyResourceCreateTime;
	private Date authoritiyResourceUpadteTime;
	private Boolean enabled;
	private CmsAuthoritiy cmsAuthoritiy;
	private CmsResource cmsResource;
	
	
	public CmsAuthoritiyResource() {
	}
	
	public CmsAuthoritiyResource(Long authoritiyResourceId,
			Date authoritiyResourceCreateTime,
			Date authoritiyResourceUpadteTime, Boolean enabled,
			CmsAuthoritiy cmsAuthoritiy, CmsResource cmsResource) {
		this.authoritiyResourceId = authoritiyResourceId;
		this.authoritiyResourceCreateTime = authoritiyResourceCreateTime;
		this.authoritiyResourceUpadteTime = authoritiyResourceUpadteTime;
		this.enabled = enabled;
		this.cmsAuthoritiy = cmsAuthoritiy;
		this.cmsResource = cmsResource;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAuthoritiyResourceId() {
		return authoritiyResourceId;
	}

	public void setAuthoritiyResourceId(Long authoritiyResourceId) {
		this.authoritiyResourceId = authoritiyResourceId;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAuthoritiyResourceCreateTime() {
		return authoritiyResourceCreateTime;
	}

	public void setAuthoritiyResourceCreateTime(Date authoritiyResourceCreateTime) {
		this.authoritiyResourceCreateTime = authoritiyResourceCreateTime;
	}

	@Temporal(TemporalType.TIMESTAMP) 
	@JsonSerialize(using = JackDateSerializer.class)
	public Date getAuthoritiyResourceUpadteTime() {
		return authoritiyResourceUpadteTime;
	}

	public void setAuthoritiyResourceUpadteTime(Date authoritiyResourceUpadteTime) {
		this.authoritiyResourceUpadteTime = authoritiyResourceUpadteTime;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public CmsAuthoritiy getCmsAuthoritiy() {
		return cmsAuthoritiy;
	}

	public void setCmsAuthoritiy(CmsAuthoritiy cmsAuthoritiy) {
		this.cmsAuthoritiy = cmsAuthoritiy;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public CmsResource getCmsResource() {
		return cmsResource;
	}

	public void setCmsResource(CmsResource cmsResource) {
		this.cmsResource = cmsResource;
	}
	
}
