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
			<p>Comment tool plans</p>
		</div>
		<form id="commentToolPlanForm" name="commentToolPlanForm">
		        <div class="upperButtonBar">
        			<div class="buttonBar">
        				<input class="formButton" onclick="validateAndSubmit()" value="OK" type="button"> 
        				<input class="formButton" value="Cancel" type="button" onclick="goBack()">
        			</div>
        		</div>
				<div class="stepIntro">
        			Please give the comment for the following tool plans:<br/>
        			<div id="toolPlanTable"></div>
        			<input type="hidden" name="selectedToolPlanIds" id="selectedToolPlanIds" value="${selectedToolPlans}"/>
        		</div>
        		<div class="requiredfield">
        			* Indicates required fields
        		</div>
        		<table class="formTable">
        			<tbody>
        				<tr id="originatingDestinationCodeRow" class="row">
        					<td>
        						<label>
        							<span class="requiredfield">*</span>Comment:
        						</label>
        					</td>
        					<td class="inputField">
        					    <textarea rows="7" cols="80" id="comment" name="comment"></textarea>
        					</td>
        					<td id="requiredComment">
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
	var formId = '#commentToolPlanForm';
	commentField = document.getElementById("comment").value;
	if(commentField == "")
	{
		document.getElementById("requiredComment").innerHTML="Comment is required.";
		return;
	}else if(commentField.length > 500){
		document.getElementById("requiredComment").innerHTML="Maximum 500 characters.";
		return;
	}
	SEVENJ.validateAndSubmit(formId,'commentToolPlans.htm', commentSuccess);
}

function commentSuccess(){
	SEVENJ.redirect('viewPricePlan.htm?pricePlanCode=${webSession.currentPricePlan.pricePlanCode}');
}
function goBack() {
	window.history.back();
}
</script>