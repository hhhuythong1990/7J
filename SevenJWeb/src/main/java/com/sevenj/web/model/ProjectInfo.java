package com.sevenj.web.model;

public class ProjectInfo {
	private String projectId;
	private String projectName;
	private String projectPeat;

	public ProjectInfo(String projectId, String projectName, String projectPeat) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectPeat = projectPeat;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectPeat() {
		return projectPeat;
	}

	public void setProjectPeat(String projectPeat) {
		this.projectPeat = projectPeat;
	}

}