<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld"%>
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
		<div class="introText" style="border-bottom: 1px dotted #CCCCCC;">
			<p>Create new price plan page for project #${webSession.currentProject.projectId }</p>
		</div>
		<div class="stepIntro" style="margin-top: 10px;">The following properties will be used to
		create your new price plan</div>
		<div class="requiredfield" style="color: #666666;margin-left: 10px;">* Indicates required fields</div>
		<div style="border-bottom: 1px dotted #CCCCCC;margin: 0px 0px 10px 0px;"></div>
		<!-- <form id="createNewPricePlanForm" name="createNewPricePlanForm" class="jtable-dialog-form jtable-create-form" style="margin-top: 0px; width:100%">-->
		<spring:form commandName="pricePlan" id="createNewPricePlanForm" name="createNewPricePlanForm">
			<table class="formTable" style="width: 100%;">
				<tbody class="rowIntro">
					<tr>
						<td width="20%">
							<span class="dialog-info" >
								Price plan code (4 characters)
							</span><br>
						</td>
					</tr>
					<tr >
						<td width="20%"><label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield">*</span>Price plan code: </label>
						</td>
						<td width="40%">
							<div>
								<spring:input path="pricePlanCode" id="pricePlanCode" type="text"
									name="pricePlanCode" style="width:45px" maxlength="4" />
							</div>
							
						</td>
						<td width="40%"><spring:errors path="pricePlanCode" class="errorsPricePlanCode"/></td>
					
					</tr>
					<tr>
						<td colspan="3">
							<div style="border-top: 1px dotted #CCCCCC; margin: 10px 0px 10px 0px;"></div>
						</td>
					</tr>					
					<tr >
						<td colspan="3" >
							<span class="dialog-info"> What would you like to name your new Price Plan <br></span>
						</td>
					</tr>
					<tr >
						<td width="20%">
							<label style="margin:10px 0px 20px 2px"> 
							<span class="requiredfield">*</span>Price plan name: </label>
						</td>
						<td width="40%">
							<spring:input path="pricePlanName" id="pricePlanName"  type="text" name="pricePlanName" style="width:200px" maxlength="255"/>
						</td>
						<td width="40%"><spring:errors path="pricePlanName" class="errorsPricePlanName"/></td>
					</tr>
					<tr>
						<td colspan="3">
							<div style= "margin: 20px 0px 20px 0px;"></div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="upperButtonBar">
								<div class="buttonBar">
									<input class="formButton" value="OK" type="submit">
									<input class="formButton" value="Cancel" type="button" onclick="goBack()">
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</spring:form>
		
	</div>
</div>
<script>
$(document).ready(function () {
	
});
function goBack(){
	SEVENJ.redirect("viewProject.htm?projectId=${webSession.currentProject.projectId}");
}
</script>