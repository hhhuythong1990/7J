<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Import a Price Plan</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="contenttable">
		<div class="introText">
			<p>Import data from existing price plan</p>
		</div>
		  <form id="importToolPlans" name="importToolPlans">
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input type="button" class="formButton" value="OK" onclick="validateAndSubmit()"/>
					<input type="button" class="formButton" value="Cancel" onclick="goBack()"/>
				</div>
			</div>
			<div class="requiredfield">
				Please select the source price plan:
			</div>
			<table class="formTable">
				<colgroup>
					<col class="labelCol"/>
					<col class="inputCol"/>
				</colgroup>
				<tbody>
					<tr id="originatingDestinationCodeRow" class="row">
						<td>
							<label>
								Source price plan:
							</label>
						</td>
						<td class="inputField">
							<div>
								<select id="sourcePricePlanCode" name="sourcePricePlanCode" class="validate[required]">
								   <option value=""></option>
								   <c:forEach var="item" items="${pricePlans}">
								   		<option value="${item.pricePlanCode }">${item.pricePlanName}</option>
								   </c:forEach>
								</select>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div id="pricePlanInformation" class="introText" style="padding-left: 5px"></div>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input type="button" class="formButton" value="OK" onclick="validateAndSubmit()"/>
					<input type="button" class="formButton" value="Cancel" onclick="goBack()"/>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
$(document).ready(function () {
	$('#sourcePricePlanCode').change(function(){
		 var pricePlanCodeNew = $('#sourcePricePlanCode').val();
		 $.ajax({
	   		 type: 'GET',
	            url: 'getNumberOfEligibleToolPlansForImport.htm',
	            data: 'pricePlanCode='+pricePlanCodeNew,
	            success : function(data){
	            	 $('#pricePlanInformation').html('There are ' + data.TotalRecordCount + ' tool plan(s) will be imported.');
	            },
	            error : function(){
	           	 SEVENJ.showFatalError();
	            }
	        });
	});
});
function validateAndSubmit(){
	var formId = '#importToolPlans';
	SEVENJ.validateAndSubmit(formId,'importPricePlan.htm', importSuccess);
}

function importSuccess(){
	SEVENJ.redirect('viewPricePlan.htm?pricePlanCode=${webSession.currentPricePlan.pricePlanCode}&selectedTab=0');
}
function goBack(){
	window.history.back();
}
</script>