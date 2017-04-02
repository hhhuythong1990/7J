<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>7J Table Management</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="contenttable">
		<div class="introText">
			<p>Copy Price Plan Records</p>
		</div>
		<form id="copyToolPlanForm" name="copyToolPlanForm">
		        <div class="upperButtonBar">
        			<div class="buttonBar">
        				<input class="formButton" onclick="validateAndSubmit()" value="OK" type="button"> 
        				<input class="formButton" value="Cancel" type="button" onclick="goBack()">
        			</div>
        		</div>
				<div class="stepIntro">
        			The following row(s) were selected to be copied: <br/>
        			<div id="toolPlanTable"></div>
        			<input type="hidden" name="selectedToolPlanIds" id="selectedToolPlanIds" value="${selectedToolPlans}"/>
        		</div>
        		<div class="stepIntro">
        			Please select the destination price plan:
        		</div>
        		<table class="formTable">
        			<tbody>
        				<tr id="originatingDestinationCodeRow" class="row">
        					<td>
        						<label>
        							<span class="requiredfield">*</span>Destination price plan:
        						</label>
        					</td>
        					<td class="inputField">
        						<div>
        							<select id="destinationPricePlanCode" name="destinationPricePlanCode">
        							   <option value=""></option>
        							   <c:forEach var="item" items="${pricePlans}">
        							   		<option value="${item.pricePlanCode }">${item.pricePlanName}</option>
        							   </c:forEach>
        							</select>
        						</div>
        					</td>
        					<td id="destinationPricePlanRequired">
        						&nbsp;
        					</td>
        				</tr>
        			</tbody>
        		</table>
        		<div class="upperButtonBar">
        			<div class="buttonBar">
        				<input class="formButton" onclick="validateAndSubmit()" value="OK" type="button"> 
        				<input class="formButton" value="Cancel" type="button" onclick="goBack()">
        			</div>
        		</div>
		</form>
	</div>
</div>
<script>
$(document).ready(function(){
	// setup jtable
	SEVENJ.setupToolPlanTable('#toolPlanTable','getSelectedToolPlans.htm?selectedToolPlanIds=${selectedToolPlans}',false,'${webSession.currentUser.username}');
	//load jtable
	$("#toolPlanTable").jtable('load');
});

function validateAndSubmit(){
	var formId = '#copyToolPlanForm';
	field = document.getElementById("destinationPricePlanCode").value;
	if(field == "")
	{
		document.getElementById("destinationPricePlanRequired").innerHTML="Destination price plan is required.";
		return;
	}
	SEVENJ.validateAndSubmit(formId,'copyToolPlans.htm', copySuccess);
}

function copySuccess(){
	SEVENJ.redirect('viewPricePlan.htm?pricePlanCode=' + $('#destinationPricePlanCode').val() + '&selectedTab=0');
}
function goBack() {
	window.history.back();
}
</script>