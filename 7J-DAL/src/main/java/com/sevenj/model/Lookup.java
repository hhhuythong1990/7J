package com.sevenj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="admisellaneous")
public class Lookup {
	private int id;
	private String description;
	private int type;
	private String typeName;
	private int inheritFromGeneral;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("ID")
	public int getId() {
		return id;
	}
	
	
	@Column(name="Description")
	@JsonProperty("Description")
	public String getDescription() {
		return description;
	}
	
	@Column(name="Type")
	@JsonProperty("Type")
	public int getType() {
		return type;
	}

	
	@Column(name="TypeName")
	@JsonProperty("TypeName")
	public String getTypeName() {
		return typeName;
	}

	@Column(name="InheritFromGeneral")
	@JsonProperty("InheritFromGeneral")
	public int getInheritFromGeneral() {
		return inheritFromGeneral;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setType(int type) {
		this.type = type;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public void setInheritFromGeneral(int inheritFromGeneral) {
		this.inheritFromGeneral = inheritFromGeneral;
	}
}
