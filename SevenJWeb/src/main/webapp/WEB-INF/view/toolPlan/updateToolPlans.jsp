<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>
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
			<p>Update tool plans</p>
		</div>
		<spring:form id="updateToolPlanForm" name="updateToolPlanForm" action="updateToolPlans.htm" commandName="updateToolPlanInfo" method="POST">
		        <div class="upperButtonBar">
        			<div class="buttonBar">
        				<input class="formButton" value="OK" type="submit"> 
        				<input class="formButton" value="Cancel" type="button" onclick="SEVENJ.gobackToViewPricePlan()">
        			</div>
        		</div>
        		<div class="introText">
		  			Update the following price plan record(s) for Price Plan
	        	</div>
	        	<div class="tabletitle">
	        		Code: ${webSession.currentPricePlan.pricePlanCode }   Name: ${webSession.currentPricePlan.pricePlanName }
	        	</div>
	        	<div class="introText">
			  		Price Plan record information
	        	</div>
				<div class="stepIntro">
        			<div id="toolPlanTable"></div>
        			<spring:input type="hidden" path="selectedToolPlanIds" value="${ selectedToolPlans}"/>
        		</div>
        		
        		<div class="stepIntro">
        			Please enter the new value for update (keep blank for unchange)<br/>
        		</div>
        		<table class="formTable">
        			<colgroup>	
        				<col class="labelCol" style="width:30%"/>
        				<col class="inputCol"/>
        			</colgroup>
        			<tbody>
        				<tr id="rateRow" class="row">
        					<td>
        						<label>
        							Rate (-1 for unchange):
        						</label>
        					</td>
        					<td class="inputField">
        						<div>
        							<spring:input path="rate"/>
        						</div>
        					</td>
        					<td><spring:errors path="rate"/></td>
        				</tr>
        				<tr id="tableEntryEffectiveDate" class="row">
        					<td>
        						<label>
        							Table entry effective date (YYYY-mm-dd):
        						</label>
        					</td>
        					<td class="inputField">
        						<div>
        							<spring:input path="tableEntryEffectiveDate"/>
        						</div>
        					</td>
        					<td><spring:errors path="tableEntryEffectiveDate"/></td>
        				</tr>
        				<tr id="tableEntryExpirationDate" class="row">
        					<td>
        						<label>
        							Table entry expiration date (YYYY-mm-dd):
        						</label>
        					</td>
        					<td class="inputField">
        						<div>
        							<spring:input path="tableEntryExpirationDate"/>
        						</div>
        					</td>
        					<td><spring:errors path="tableEntryExpirationDate"/></td>
        				</tr>
        			</tbody>
        		</table>
        		<div class="upperButtonBar">
        			<div class="buttonBar">
        				<input class="formButton" value="OK" type="submit"> 
        				<input class="formButton" value="Cancel" type="button" onclick="SEVENJ.gobackToViewPricePlan()">
        			</div>
        		</div>
		</spring:form>
	</div>
</div>
<script>
$(document).ready(function(){
	// setup jtable
	SEVENJ.setupToolPlanTable('#toolPlanTable','getSelectedToolPlans.htm?selectedToolPlanIds=${selectedToolPlans}',false,'${webSession.currentUser.username}');
	//load jtable
	$("#toolPlanTable").jtable('load');
});
</script>