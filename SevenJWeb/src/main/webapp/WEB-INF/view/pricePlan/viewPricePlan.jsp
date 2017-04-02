<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Price Plan Management - Project #${webSession.currentProject.projectId } '${webSession.currentProject.projectName }'</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="bell-menu bell-menu-single clearfix">
		<div class="menu-wrapper">
			<ul>
				<li id="tabRejected" title="My Tool Plans Rejected - Tab" style="cursor: pointer;"><a><span class="tab">Rejected</span></a></li>
				<li id="tabPending" title="My Tool Plans Pending - Tab" style="cursor: pointer;"><a><span class="tab">Pending</span></a></li>
				<li id="tabApproved" title="My Tool Plans Aprroved - Tab" style="cursor: pointer;"><a><span class="tab">Approved</span></a></li>
				<!--  <li id="tabAll" class="" title="All My Tool Plans- Tab" style="cursor: pointer;"><a><span class="tab">All</span></a></li>-->
			</ul>
		</div>
	</div>
	<div class="contenttable" id="tabContent"></div>
</div>
<script>
	$(document).ready(function () {
		loadDefaultTab();
		$(tabApprovedId).click(function(){
			loadApproveTab();
		});
		$(tabPendingId).click(function(){
			loadPendingTab();
		});
		$(tabRejectedId).click(function(){
			loadRejectedTab();
		});
		
		$(tabAll).click(function(){
			SEVENJ.loadTabContent(tabAll,'#tabContent','searchToolPlans.htm');
		});
	});
	var tabs = [tabPendingId, tabApprovedId, tabRejectedId];
	function loadDefaultTab(){
		var currentTab = '${ webSession.currentToolPlanStatusTab}';
		if (currentTab == '-1'){
			loadApproveTab();
		}else{
			SEVENJ.loadTabContent(tabs[currentTab],'#tabContent','getToolPlanList.htm?status=' + currentTab);
		}
	}
	function loadApproveTab(){
		SEVENJ.loadTabContent(tabApprovedId,'#tabContent','getToolPlanList.htm?status=1');
	}
	function loadPendingTab(){
		SEVENJ.loadTabContent(tabPendingId,'#tabContent','getToolPlanList.htm?status=0');
	}
	function loadRejectedTab(){
		SEVENJ.loadTabContent(tabRejectedId,'#tabContent','getToolPlanList.htm?status=2');
	}
</script>

