<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>

<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Additional Price Plan Info Management</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="contenttable">
		<form id="additionalPricePlanForm" name="additionalPricePlanForm">
			<div class="introText">
				<p>Edit Additional Price Plan Info</p>
			</div>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="OK" type="button"
						onclick="validateAndSubmit()"> <input class="formButton"
						value="Cancel" type="button" onclick="goBack()">
				</div>
			</div>
			<table class="formTable">
				<colgroup>
					<col class="labelCol">
					<col class="inputCol">
					<col class="paddingCol">
				</colgroup>
				<tbody>
					<tr class="rowIntro">
						<td colspan="3"><span class="dialog-info">Pricing Plan Information <br>
						</span></td>
					</tr>
					<tr class="row">
						<td><label>*Pricing Plan Code (4 chars):
						</label></td>
						<td class="inputField">
							<div>
								<input id="pricePlanCode" type="text"
									name="pricePlanCode" style="width: 170px" maxlength="4" value="${entity.pricePlanCode}" readonly="readonly">
							</div>
						</td>
						<td id="pricePlanCodeRequired"></td>
					</tr>
					<tr id="PricingPlanName" class="row">
						<td width="25%"><label >*Pricing Plan Name:
						</label></td>
						<td class="inputField">
							<div>
								<input id="pricePlanName" type="text"
									name="pricePlanName" style="width: 170px" maxlength="256" value="${entity.pricePlanName}" readonly="readonly">

							</div>
						</td>
						<td id="pricePlanNameRequired"></td>
					</tr>
					<tr id="planType" class="row">
						<td><label>*Plan Type:
						</label></td>
						<td class="inputField">
							<div>
								<select name="planType">
								    <c:forEach var="item" items="${ planTypes }">
										<option value="${item.id }" <c:if test="${item.id eq entity.planType}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>	
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr id="description" class="row">
						<td valign="top"><label>Description:
						</label></td>
						<td class="inputField" colspan="2">
							<div>
								<%-- <textarea rows="7" cols="80" id="description" name="description">${entity.description }</textarea> --%>
								<textarea class="ckeditor"  rows="7" cols="80" id="description" name="description">${entity.description }</textarea>
							</div>
						</td>
					</tr>
					<tr id="accountLevel" class="row">
						<td><label>*Account Level:
						</label></td>
						<td class="inputField">
							<div>
								<select name="accountLevel">
								    <c:forEach var="item" items="${ generals }">
										<option value="${item.id }" <c:if test="${item.id eq entity.accountLevel}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>		
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					
					<tr id="lineLevel" class="row">
						<td><label>*Line Level:
						</label></td>
						<td class="inputField">
							<div>
								<select name="lineLevel">
								    <c:forEach var="item" items="${ generals }">
										<option value="${item.id }" <c:if test="${item.id eq entity.lineLevel}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>		
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr id="priorityCode" class="row">
						<td><label>*PriorityCode:
						</label></td>
						<td class="inputField">
							<div>
								<select name="priorityCode">
									<c:forEach var="item" items="${ priorityCodes}">
										<option value="${item.id }" <c:if test="${item.id eq entity.priorityCode}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
								<input class="inputField" name="prioprityCodeInput" id="prioprityCodeInput" type="text" maxlength="3">
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*USOC: </label></td>
						<td class="inputField">
							<div>
								<input class="inputField" name="usoc" id="usoc" type="text" value="${entity.usoc}">
							</div>
						</td>
						<td id="usocRequired"></td>
					</tr>
					<tr class="row">
						<td><label>*Tool Pricing Plan:
						</label></td>
						<td class="inputField">
							<div>
								<select name="isToolPricingPlan">
									<c:forEach var="item" items="${ generals }">
										<option value="${item.id }" <c:if test="${item.id eq entity.isToolPricingPlan}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${ isToolPricingPlans}">
										<option value="${item.id }" <c:if test="${item.id eq entity.isToolPricingPlan}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
							</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*No Overseas Destinations:
						</label></td>
						<td class="inputField">
							<div>
								<select name="noOverseaDest" id="noOverseaDest">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.noOverseaDest}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${ noOverseaDest}">
										<option value="${item.id }" <c:if test="${item.id eq entity.noOverseaDest}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Top 28 Overseas Destinations ONLY:
						</label></td>
						<td class="inputField">
							<div>
								<select name="t28OverseaDest">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.top28OverseaDest}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Top 28 &amp; 29+ Overseas Destinations:
						</label></td>
						<td class="inputField">
							<div>
								<select name="t29OverseaDest">
								<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.t29OverseaDest}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								<c:forEach var="item" items="${t29OverseaDests}">
										<option value="${item.id }" <c:if test="${item.id eq entity.t29OverseaDest}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Block of Time:
						</label></td>
						<td class="inputField">
							<div>
								<select name="blockOfTime">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.blockOfTime}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${blockOfTimes}">
										<option value="${item.id }" <c:if test="${item.id eq entity.blockOfTime}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
								<input class="inputField" name="blockOfTimeInput" id="blockOfTimeInput" type="text" maxlength="256">
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Dollar CAP:
						</label></td>
						<td class="inputField">
							<div>
								<select name="dollarCap">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.dollarCap}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${dollarCaps}">
										<option value="${item.id }" <c:if test="${item.id eq entity.dollarCap}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Bill Minium:
						</label></td>
						<td class="inputField">
							<div>
								<select name="billMinium">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.billMinium}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${billMiniums}">
										<option value="${item.id }" <c:if test="${item.id eq entity.billMinium}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Quantity of Blocks:
						</label></td>
						<td class="inputField">
							<div>
								<select name="quantity">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.quantity}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${quantitys}">
										<option value="${item.id }" <c:if test="${item.id eq entity.quantity}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Short Haul Rule:
						</label></td>
						<td class="inputField">
							<div>
								<select name="shortHaulRule">
								<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.shortHaulRule}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Cost Comparison Rule:
						</label></td>
						<td class="inputField">
							<div>
								<select name="costCompareOnRule">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.costCompareOnRule}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Calling Card Surcharges:
						</label></td>
						<td class="inputField">
							<div>
								<select name="withSurcharges">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.withSurcharges}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Multiple Rate Schedules:
						</label></td>
						<td class="inputField">
							<div>
								<select name="multiRateSchedule">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.multiRateSchedule}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Airtime:
						</label></td>
						<td class="inputField">
							<div>
								<select name="airTime">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.airTime}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${airTimes}">
										<option value="${item.id }" <c:if test="${item.id eq entity.airTime}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Volume Discount:
						</label></td>
						<td class="inputField">
							<div>
								<select name="volumeDiscount">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.volumeDiscount}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Contract Discount:
						</label></td>
						<td class="inputField">
							<div>
								<select name="contractDiscount">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.contractDiscount}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*60/60 Increments ONLY:
						</label></td>
						<td class="inputField">
							<div>
								<select name="increments60">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments60}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${increments60s}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments60}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*60/60 or 30/6 Increments:
						</label></td>
						<td class="inputField">
							<div>
								<select name="increments60or30">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments60or30}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${increments60or30s}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments60or30}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*30/6 Increments:
						</label></td>
						<td class="inputField">
							<div>
								<select name="increments30">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments30}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${increments30s}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments30}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*10/10 increments:
						</label></td>
						<td class="inputField">
							<div>
								<select name="increments10">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments10}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${increments10s}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments10}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*1/1 increments:
						</label></td>
						<td class="inputField">
							<div>
								<select name="increments1">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments1}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
									<c:forEach var="item" items="${increments1s}">
										<option value="${item.id }" <c:if test="${item.id eq entity.increments1}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Peak Off-Peak:
						</label></td>
						<td class="inputField">
							<div>
								<select name="peakOffPeak">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.peakOffPeak}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*24/7 Time Frame:
						</label></td>
						<td class="inputField">
							<div>
								<select name="is247">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.is247}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*TDH1 - Handicap Discount:
						</label></td>
						<td class="inputField">
							<div>
								<select name="thd1">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.thd1}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Provincial Ontario Only:
						</label></td>
						<td class="inputField">
							<div>
								<select name="provincialOntarioOnly">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.provincialOntarioOnly}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
					<tr class="row">
						<td><label>*Provincial Quebec Only:
						</label></td>
						<td class="inputField">
							<div>
								<select name="provincialQuebecOnly">
									<c:forEach var="item" items="${ generals}">
										<option value="${item.id }" <c:if test="${item.id eq entity.provincialQuebecOnly}">selected="selected"</c:if> >${item.description }</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="OK" type="button" onclick="validateAndSubmit()">
					 <input class="formButton" value="Cancel" type="button" onclick="goBack()">
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	var formId = '#additionalPricePlanForm';
	$(document).ready(function() {
		$(formId).validationEngine({
			'binded' : false
		});
		$('#btnCancel').click(function() {
		});
	});

	function validateAndSubmit() {
		/*pricePlanCodeField = document.getElementById("pricePlanCode").value;
		if(pricePlanCodeField == "")
		{
			document.getElementById("pricePlanCodeRequired").innerHTML="Price Plan Code is required.";
			return;
		}else if(pricePlanCodeField.length > 4){
			document.getElementById("pricePlanCodeRequired").innerHTML="Maximum 4 characters.";
			return;
		}
		
		pricePlanNameField = document.getElementById("pricePlanName").value;
		if(pricePlanNameField == "")
		{
			document.getElementById("pricePlanNameRequired").innerHTML="Price Plan Name is required.";
			return;
		}else if(pricePlanNameField.length > 256){
			document.getElementById("pricePlanNameRequired").innerHTML="Maximum 256 characters.";
			return;
		}*/
		
		usocField = document.getElementById("usoc").value;
		if(usocField == "")
		{
			document.getElementById("usocRequired").innerHTML="USOC is required.";
			return;
		}else if(usocField.length > 256){
			document.getElementById("usocRequired").innerHTML="Maximum 256 characters.";
			return;
		}else{
			document.getElementById("usocRequired").innerHTML="";
		}
		
		// get data form CKEDITOR
		var des = CKEDITOR.instances.description.getData();
		$("textarea#description").html(des);
		
		SEVENJ.ajaxSubmit(formId, 'editAdditionalPricePlan.htm', saveAdditionalPricePlanCallback);
		
		/* $(formId).validationEngine('hide');
		$(formId).validationEngine('detach');
		$(formId).validationEngine({
			'binded' : false
		});
		var isValid = $(formId).validationEngine('validate');
		if (isValid) {
			SEVENJ.ajaxSubmit(formId, 'editAdditionalPricePlan.htm',
					saveAdditionalPricePlanCallback);
			
		} */
	};

	function saveAdditionalPricePlanCallback() {
		SEVENJ.redirect("getAdditionalPricePlanList.htm");
	}

	function goBack() {
		window.history.back();
	}
</script>