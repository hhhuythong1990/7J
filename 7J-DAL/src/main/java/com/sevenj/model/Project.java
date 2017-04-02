package com.sevenj.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;


@Entity
@Table(name="project")
public class Project implements Serializable {
	private String peat;
	private String projectId;
	private String projectName;
	private Date createdDate;
	private String createdUser;
	private Set<PricePlan> pricePlans;

	@Id
	@Column(name="ProjectId", unique = true, nullable= false)
	@JsonProperty("projectId")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name="ProjectName", nullable = false)
	@JsonProperty("projectName")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name = "Peat", nullable = false)
	@JsonProperty("peat")
	public String getPeat() {
		return peat;
	}

	public void setPeat(String peat) {
		this.peat = peat;
	}

	@Column(name="CreationDate", nullable = false)
	@JsonProperty("createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="CreationUser", nullable = false)
	@JsonProperty("createdUser")
	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="project")
	@JsonIgnore
	public Set<PricePlan> getPricePlans() {
		return pricePlans;
	}

	public void setPricePlans(Set<PricePlan> pricePlans) {
		this.pricePlans = pricePlans;
	}
	
	

}
