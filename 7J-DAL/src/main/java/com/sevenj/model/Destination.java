package com.sevenj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="destination")
public class Destination {
	private String destinationCode;
	private String destinationDescription;
	private int sortOrder;

	@Id
	@Column(name="DestinationCode", nullable = false)
	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	@Column(name="DestinationDescription", nullable = false)
	public String getDestinationDescription() {
		return destinationDescription;
	}

	public void setDestinationDescription(String destinationDescription) {
		this.destinationDescription = destinationDescription;
	}

	@Column(name="SortOrder", nullable = true)
	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

}
