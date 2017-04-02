package com.sevenj.web.common;

import com.sevenj.model.Project;
import com.sevenj.model.SearchToolPlanCriteria;
import com.sevenj.model.User;
import com.sevenj.web.model.PricePlanInfo;
import com.sevenj.web.model.ProjectInfo;

public class WebSession {
	private User currentUser;
	private ProjectInfo currentProject;
	private PricePlanInfo currentPricePlan;
	private int currentToolPlanStatusTab;
	private int currentPricePlanStatusTab;
	private SearchToolPlanCriteria searchToolPlanCriteria;

	public WebSession(){
		currentToolPlanStatusTab = -1;
		currentPricePlanStatusTab = -1;
	}
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ProjectInfo getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(ProjectInfo currentProject) {
		this.currentProject = currentProject;
	}

	public PricePlanInfo getCurrentPricePlan() {
		return currentPricePlan;
	}

	public void setCurrentPricePlan(PricePlanInfo currentPricePlan) {
		this.currentPricePlan = currentPricePlan;
	}

	public int getCurrentToolPlanStatusTab() {
		return currentToolPlanStatusTab;
	}

	public void setCurrentToolPlanStatusTab(int currentToolPlanStatusTab) {
		this.currentToolPlanStatusTab = currentToolPlanStatusTab;
	}
	public int getCurrentPricePlanStatusTab() {
		return currentPricePlanStatusTab;
	}
	public void setCurrentPricePlanStatusTab(int currentPricePlanStatusTab) {
		this.currentPricePlanStatusTab = currentPricePlanStatusTab;
	}
	public SearchToolPlanCriteria getSearchToolPlanCriteria() {
		return searchToolPlanCriteria;
	}
	public void setSearchToolPlanCriteria(
			SearchToolPlanCriteria searchToolPlanCriteria) {
		this.searchToolPlanCriteria = searchToolPlanCriteria;
	}
}
