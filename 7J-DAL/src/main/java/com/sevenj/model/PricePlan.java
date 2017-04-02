package com.sevenj.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "priceplan")
public class PricePlan {
	private String pricePlanCode;
	private String pricePlanName;
	private Date creationDate;
	private String creationUser;
	private int pricePlanStatus;
	private Project project;
	private int CBSSStatus;
	private Set<ToolPlan> toolPlans; 

	@Id
	@Column(name="PricePlanCode", nullable = false)
	@JsonProperty("pricePlanCode")
	@Size(max=4,min=4)
	@NotEmpty
	public String getPricePlanCode() {
		return pricePlanCode;
	}

	public void setPricePlanCode(String pricePlanCode) {
		this.pricePlanCode = pricePlanCode;
	}

	@Column(name="PricePlanName", nullable = false)
	@JsonProperty("pricePlanName")
	@Size(max=255)
	@NotEmpty
	public String getPricePlanName() {
		return pricePlanName;
	}

	public void setPricePlanName(String pricePlanName) {
		this.pricePlanName = pricePlanName;
	}

	@Column(name="CreationDate", nullable=false)
	@JsonProperty("creationDate")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name="CreationUser", nullable=false)
	@JsonProperty("creationUser")
	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Column(name="PricePlanStatus", nullable=false)
	public int getPricePlanStatus() {
		return pricePlanStatus;
	}

	public void setPricePlanStatus(int pricePlanStatus) {
		this.pricePlanStatus = pricePlanStatus;
	}

	@ManyToOne
	@JoinColumn(name="ProjectID", nullable=false)
	@JsonIgnore
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name="CBSSStatus", nullable=false)
	@JsonProperty("CBSSStatus")
	public int getCBSSStatus() {
		return CBSSStatus;
	}

	public void setCBSSStatus(int cBSSStatus) {
		CBSSStatus = cBSSStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="pricePlan")
	@JsonIgnore
	public Set<ToolPlan> getToolPlans() {
		return toolPlans;
	}

	public void setToolPlans(Set<ToolPlan> toolPlans) {
		this.toolPlans = toolPlans;
	}
	
	

}
