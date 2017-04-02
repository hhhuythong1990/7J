<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring-form.tld" %>
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
		<spring:form id="additionalPricePlanForm" name="additionalPricePlanForm" action="createADPricePlan.htm" commandName="additionalPricePlanView" method="POST">
			<div class="introText">
				<p>Create New Additional Price Plan Info</p>
			</div>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="OK" type="submit"/> 
					<input class="formButton" value="Cancel" type="button" onclick="goBack()">
				</div>
				<div class="stepIntro">
				The following properties will be used to create your Additional Price Plan Info.<br>
				</div>
				<div class="requiredfield">* Indicates required fields</div>
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
						<td>*Pricing Plan Code (4 chars):</td>
						<td class="inputField" style="width: 170px">
							<div>
								<spring:input path="pricePlanCode" maxlength="4"/>
							</div>
						</td>
						<td><spring:errors path="pricePlanCode"/></td>
					</tr>
					<tr id="PricingPlanName" class="row">
						<td width="25%">*Pricing Plan Name:</td>
						<td class="inputField">
							<div>
								<spring:input path="pricePlanName" maxlength="256"/>
							</div>
						</td>
						<td><spring:errors path="pricePlanName"/></td>
					</tr>
					<tr id="planType" class="row">
						<td>*Plan Type:</td>
						<td class="inputField">
							<div>
								<spring:select path="planType">
								    <c:forEach var="item" items="${ planTypes }">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>	
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="planType"/></td>
					</tr>
					<tr id="description" class="row">
						<td valign="top">Description:</td>
						<td class="inputField">
							<div>
								<spring:textarea cssClass="ckeditor" rows="7" cols="80" path="description"/>
							</div>
						</td>
						<td><spring:errors path="description"/></td>
					</tr>
					<tr id="accountLevel" class="row">
						<td>*Account Level:</td>
						<td class="inputField">
							<div>
								<spring:select path="accountLevel">
								    <c:forEach var="item" items="${ generals }">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>		
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="accountLevel"/></td>
					</tr>
					
					<tr id="lineLevel" class="row">
						<td>*Line Level:</td>
						<td class="inputField">
							<div>
								<spring:select path="lineLevel">
								    <c:forEach var="item" items="${ generals }">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>		
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="lineLevel"/></td>
					</tr>
					<tr id="priorityCode" class="row">
						<td>*PriorityCode:</td>
						<td class="inputField">
							<div>
								<spring:select path="priorityCode">
									<c:forEach var="item" items="${ priorityCodes}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
								<spring:input path="priorityCodeNew" maxlength="3"/>
							</div>
						</td>
						<td><spring:errors path="priorityCode"/></td>
					</tr>
					<tr class="row">
						<td>USOC:</td>
						<td class="inputField">
							<div>
								<spring:input path="usoc" maxlength="256"/>
							</div>
						</td>
						<td><spring:errors path="usoc"/></td>
					</tr>
					<tr class="row">
						<td>*Toll Pricing Plan:</td>
						<td class="inputField">
							<div>
								<spring:select path="isToolPricingPlan">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${ isToolPricingPlans}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
							   </spring:select>
							</div>
						</td>
						<td><spring:errors path="isToolPricingPlan"/></td>
					</tr>
					<tr class="row">
						<td>*No Overseas Destinations:</td>
						<td class="inputField">
							<div>
								<spring:select path="noOverseaDest">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${ noOverseaDest}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="noOverseaDest"/></td>
					</tr>
					<tr class="row">
						<td>*Top 28 Overseas Destinations ONLY:</td>
						<td class="inputField">
							<div>
								<spring:select path="top28OverseaDest">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="top28OverseaDest"/></td>
					</tr>
					<tr class="row">
						<td>*Top 28 & 29+ Overseas Destinations:</td>
						<td class="inputField">
							<div>
								<spring:select path="t29OverseaDest">
								<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								<c:forEach var="item" items="${t29OverseaDests}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="t29OverseaDest"/></td>
					</tr>
					<tr class="row">
						<td>*Block of Time:</td>
						<td class="inputField">
							<div>
								<spring:select path="blockOfTime">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${blockOfTimes}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
								<spring:input path="blockOfTimeNew" maxlength="256"/>
							</div>
						</td>
						<td><spring:errors path="blockOfTime"/></td>
					</tr>
					<tr class="row">
						<td>*Dollar CAP:</td>
						<td class="inputField">
							<div>
								<spring:select path="dollarCap">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${dollarCaps}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="dollarCap"/></td>
					</tr>
					<tr class="row">
						<td>*Bill Minimum:</td>
						<td class="inputField">
							<div>
								<spring:select path="billMinium">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${billMiniums}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="billMinium"/></td>
					</tr>
					<tr class="row">
						<td>*Quantity of Blocks:</td>
						<td class="inputField">
							<div>
								<spring:select path="quantity">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${quantitys}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="quantity"/></td>
					</tr>
					<tr class="row">
						<td>*Short Haul Rule:</td>
						<td class="inputField">
							<div>
								<spring:select path="shortHaulRule">
								<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="shortHaulRule"/></td>
					</tr>
					<tr class="row">
						<td>*Cost Comparison Rule:</td>
						<td class="inputField">
							<div>
								<spring:select path="costCompareOnRule">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="costCompareOnRule"/></td>
					</tr>
					<tr class="row">
						<td>*Calling Card Surcharges:</td>
						<td class="inputField">
							<div>
								<spring:select path="withSurcharges">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="withSurcharges"/></td>
					</tr>
					<tr class="row">
						<td>*Multiple Rate Schedules:</td>
						<td class="inputField">
							<div>
								<spring:select path="multiRateSchedule">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="multiRateSchedule"/></td>
					</tr>
					<tr class="row">
						<td>*Airtime:</td>
						<td class="inputField">
							<div>
								<spring:select path="airTime">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${airTimes}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="airTime"/></td>
					</tr>
					<tr class="row">
						<td>*Volume Discount:</td>
						<td class="inputField">
							<div>
								<spring:select path="volumeDiscount">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="volumeDiscount"/></td>
					</tr>
					<tr class="row">
						<td>*Contract Discount:</td>
						<td class="inputField">
							<div>
								<spring:select path="contractDiscount">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="contractDiscount"/></td>
					</tr>
					<tr class="row">
						<td>*60/60 Increments ONLY:</td>
						<td class="inputField">
							<div>
								<spring:select path="increments60">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${increments60s}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="increments60"/></td>
					</tr>
					<tr class="row">
						<td><label>* 60/60 or 30/6 Increments:</label></td>
						<td class="inputField">
							<div>
								<spring:select path="increments60or30">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${increments60or30s}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="increments60or30"/></td>
					</tr>
					<tr class="row">
						<td>*30/6 increments:</td>
						<td class="inputField">
							<div>
								<spring:select path="increments30">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${increments30s}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="increments30"/></td>
					</tr>
					<tr class="row">
						<td>*10/10 increments:</td>
						<td class="inputField">
							<div>
								<spring:select path="increments10">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${increments10s}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="increments10"/></td>
					</tr>
					<tr class="row">
						<td>*1/1 increments:</td>
						<td class="inputField">
							<div>
								<spring:select path="increments1">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
									<c:forEach var="item" items="${increments1s}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="increments1"/></td>
					</tr>
					<tr class="row">
						<td>*Peak Off-Peak:</td>
						<td class="inputField">
							<div>
								<spring:select path="peakOffPeak">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="peakOffPeak"/></td>
					</tr>
					<tr class="row">
						<td>*24/7 Time Frame:</td>
						<td class="inputField">
							<div>
								<spring:select path="is247">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="is247"/></td>
					</tr>
					<tr class="row">
						<td>*TDH1 - Handicap Discount:</td>
						<td class="inputField">
							<div>
								<spring:select path="thd1">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="thd1"/></td>
					</tr>
					<tr class="row">
						<td>*Provincial Ontario Only:</td>
						<td class="inputField">
							<div>
								<spring:select path="provincialOntarioOnly">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="provincialOntarioOnly"/></td>
					</tr>
					<tr class="row">
						<td>*Provincial Quebec Only:</td>
						<td class="inputField">
							<div>
								<spring:select path="provincialQuebecOnly">
									<c:forEach var="item" items="${ generals}">
										<spring:option value="${item.id }" label="${item.description }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="provincialQuebecOnly"/></td>
					</tr>
				</tbody>
			</table>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="OK" type="submit">
					<input class="formButton" value="Cancel" type="button" onclick="goBack()">
				</div>
			</div>
		</spring:form>
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
		pricePlanCodeField = document.getElementById("pricePlanCode").value;
		flag = true;
		if(pricePlanCodeField == "")
		{
			document.getElementById("pricePlanCodeRequired").innerHTML="Price Plan Code is required.";
			flag = false;
		}else if(pricePlanCodeField.length > 4){
			document.getElementById("pricePlanCodeRequired").innerHTML="Maximum 4 characters.";
			flag = false;
		}else{
			document.getElementById("pricePlanCodeRequired").innerHTML="";
		}
		
		pricePlanNameField = document.getElementById("pricePlanName").value;
		if(pricePlanNameField == "")
		{
			document.getElementById("pricePlanNameRequired").innerHTML="Price Plan Name is required.";
			flag = false;
		}else if(pricePlanNameField.length > 256){
			document.getElementById("pricePlanNameRequired").innerHTML="Maximum 256 characters.";
			flag = false;
		}else{
			document.getElementById("pricePlanNameRequired").innerHTML="";
		}
		
		usocField = document.getElementById("usoc").value;
		if(usocField == "")
		{
			document.getElementById("usocRequired").innerHTML="USOC is required.";
			flag = false;
		}else if(usocField.length > 256){
			document.getElementById("usocRequired").innerHTML="Maximum 256 characters.";
			flag = false;
		}else{
			document.getElementById("usocRequired").innerHTML="";
		}

		if(!flag){
			return;
		}
		// get data form CKEDITOR
		var des = CKEDITOR.instances.description.getData();
		$("textarea#description").html(des);
		SEVENJ.ajaxSubmit(formId, 'createADPricePlan.htm', saveAdditionalPricePlanCallback);
		/* $(formId).validationEngine('hide');
		$(formId).validationEngine('detach');
		$(formId).validationEngine({
			'binded' : false
		});
		var isValid = $(formId).validationEngine('validate');
		if (isValid) {
			SEVENJ.ajaxSubmit(formId, 'createADPricePlan.htm',
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