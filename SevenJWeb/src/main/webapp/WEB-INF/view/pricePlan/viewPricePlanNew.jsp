<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld" %>
<div class="introText">
	<p>This page shows all tool plan of price plan</p>
</div>
<div class="tabletitle">
	Code: ${webSession.currentPricePlan.pricePlanCode} Name: ${webSession.currentPricePlan.pricePlanName}
	<c:if test="${webSession.currentPricePlan.status == 2}">
		<br/> Status: Approved
    </c:if>
</div>
<div>
</div>
<div class="tablectrl">
	<div id="toolbar" class="Clearfix">
		<div class="f-left col-divided-2">
			<!-- Toolbar div Back -->
			<ul>
			   <c:if test="${ webSession.currentPricePlanStatusTab == 1}">
			   		<li><a href="#" id="btnBack">Back To Price Plan</a></li>
			   </c:if>
				<c:if test="${ webSession.currentPricePlanStatusTab == 0}">
			   		<li><a href="#" id="btnBack">Back To Pending Price Plan</a></li>
			   </c:if>
			</ul>
			<!-- end Toolbar div Back -->

			<!-- Price Plan menu -->
			<ul>
				<li><a href="importPricePlan.htm">Import Exists Price Plans</a></li>
				<li><a href="createNewPricePlan.htm">Create a New Price Plan</a></li>
			</ul>
			<!-- end Price Plan menu-->

		</div>
		<div class="f-left col-divided-2">
			<!-- Price Plan Records menu -->
			<ul>
				<li><a href="createToolPlan.htm">Create Price Plan Records</a></li>
				<!-- <li><a href="#" id="searchPricePlan">Search Price Plan Rate Records</a></li> -->
				<li><a href="searchToolPricePlan.htm">Search Price Plan Rate Records</a></li>
				<li><a href="#" id="copyToolPlan">Copy Price Plan Records</a></li>
				<li><a href="#" id="updateToolPlan">Update Price Plan Records</a></li>
				<li><a href="#" id="btnAprrove">Approve Price Plan Records</a></li>
				<li><a href="#" id="btnReject">Reject Price Plan Records</a></li>
				<li><a href="#" id="btnExtract">Extract a Price Plan</a></li>
				<li><a href="#" id="commentToolPlan">Comment Price Plan Records</a></li>
				<li><a href="#" id="deleteToolPlan">Delete Price Plan Records</a></li>
			</ul>
			<!-- end price Plan records menu-->
		</div>
	</div>
	<!-- end div toolbar -->
	<div id="toolPlanPopup1"></div>
	<div id="toolPlanTable"></div>
</div>

<script>
$(document).ready(function(){
	 var clickable=false
	 var toolbar_clickable =false;
	//$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled - Adds empty span tag after ul.subnav
	
	//-------------------- group toolbar ---------------------------
		$(".myClass ").click(function() { //When trigger is clicked...
			toolbar_clickable=!toolbar_clickable;
		
			//Following events are applied to the subnav itself (moving subnav up and down)
			$(this).parent().find("div.subnav_toolbar").slideDown('fast').show(); //Drop down the subnav on click
	
			$(this).parent().hover(function() {
			}, function(){
				$(this).parent().find("div.subnav_toolbar").slideUp('fast'); //When the mouse hovers out of the subnav, move it back up
			});
			
			/*if(toolbar_clickable==false){
			$(this).parent().find("div.subnav_toolbar").slideUp('fast');
			}*/

		//Following events are applied to the trigger (Hover events for the trigger)
		}).hover(function() {
			$(this).addClass("subhover"); //On hover over, add class "subhover"
		}, function(){	//On Hover Out
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"
	});
	
	// setup jtable
	SEVENJ.setupToolPlanTable('#toolPlanTable','getToolPlans.htm',true, '${webSession.currentUser.username}');

	
	
	//load jtable
	$("#toolPlanTable").jtable('load');
	$('#btnExtract').click(function() {
		if (confirm("Do you want to export to excel file?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': 'exportToExcelToolPlan.htm'}); 
			$('#lnkDwnldLnk')[0].click();    
			document.body.removeChild(link);
	    }
	});
	$('#chkTitle').click(function() {
	    var checkboxes = $(this).closest('div[id="toolPlanTable"]').find(':checkbox');
	    if($(this).is(':checked')) {
	    	checkboxes.prop('checked', true);
	    } else {
	    	checkboxes.prop('checked', false);
	    }
	});
	
	$('#searchPricePlan').click(function(){
		SEVENJ.loadTabContent("",'#tabContent','searchToolPlans.htm');
	});
	
	$('#btnBack').click(function(){
		var tabId = SEVENJ.getCurrentTabId('${webSession.currentPricePlanStatusTab}');
		var status = 1;
		if (tabId == '#pricePlanTabId'){
			status = 1; // price plan
		}else{
			status = 0;// pending
		}
		SEVENJ.loadTabContentForPricePlan(tabId,'#tabContent','getPricePlanTabContent.htm?status='+status);
	});
	
	$('#copyToolPlan').click(function(){
		checkAndRedirect('copyToolPlans.htm');
	});
	
	$('#updateToolPlan').click(function(){
		checkAndRedirect('updateToolPlans.htm');
	});
	
	$('#commentToolPlan').click(function(){
		checkAndRedirect('commentToolPlans.htm');
	});
	
	$('#btnAprrove').click(function(){
		updateToolPlanStatus('approveToolPlan.htm');	
	});
	$('#btnReject').click(function(){
		updateToolPlanStatus('rejectToolPlan.htm');	
	});
	
	/* $('#deleteToolPlan').click(function(){
		updateToolPlanStatus('deleteToolPlans.htm');
	});
	 */
	$('#deleteToolPlan').click(function() {
		if (confirm("Are you sure want to delete the following Price Plan Rate Records?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': updateToolPlanStatus('deleteToolPlans.htm')}); 
			$('#lnkDwnldLnk')[0].click();    
			document.body.removeChild(link);
	    }
	});
});

function getSelectedToolPlan(){
	//Get all selected rows
    var selectedRows = $('#toolPlanTable').jtable('selectedRows');
    if (selectedRows.length > 0) {
    	
        //Show selected rows
       selectedRows = jQuery.map(selectedRows,function (row) {
            var record = $(row).data('record');
            return record.ID;
        });
    }
    return selectedRows;
}

function updateToolPlanStatus(actionUrl){
	$('.myClass').find("div.subnav_toolbar").css('display','none');
	processToolPlans(actionUrl, refreshToolPlans);
}

function checkAndRedirect(redirectUrl){
	var selectedToolPlans = getSelectedToolPlan();
	if (selectedToolPlans.length > 0){
		SEVENJ.redirect(redirectUrl + '?selectedToolPlanIds=' + selectedToolPlans);	
	}else{
		SEVENJ.showWarning('No price plan record selected. Please select at least one.');
	}
}

function processToolPlans(actionUrl, callback){
	var seletedToolPlans = getSelectedToolPlan();
	if (seletedToolPlans.length > 0){
		$.ajax({
	   		 type: 'POST',
	            url: actionUrl,
	            data: "selectedToolPlanIds=" + seletedToolPlans.join(","),
	            success : function(data){
	           	 if (data.Result == RESULT_OK){
	           		if (callback){
	           			callback();
	           		}
	           	 }else{
	           		 SEVENJ.showError(data.Message);
	           	 }
	            },
	            error : function(){
	           	 SEVENJ.showFatalError();
	            }
	        });
	}else{
		SEVENJ.showWarning('No price plan record selected. Please select at least one.');
	}
}

function refreshToolPlans(){
	$("#toolPlanTable").jtable('reload');
}

function copyToolPlans(){
	processToolPlans('copyToolPlans.htm',refreshToolPlans);
}

function commentToolPlans(){
	processToolPlans('commentToolPlans.htm',refreshToolPlans);
}

function showEditToolPlanPage(toolPlanId){
	SEVENJ.redirect("editToolPlan.htm?toolPlanId=" + toolPlanId);
}

function viewToolPlanPage(toolPlanId){
	SEVENJ.redirect("viewToolPlan.htm?toolPlanId=" + toolPlanId);
}
</script>

