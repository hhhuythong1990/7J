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
		<div class="introText">
			<p>Create a New Price Plan Rate Record for Price Plan Code "${webSession.currentPricePlan.pricePlanCode} "</p>
		</div>
		<spring:form id="toolPlanForm" name="toolPlanForm" action="createToolPlan.htm" method="POST" commandName="createdToolPlan">
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="OK" type="submit">
					<input class="formButton" value="Cancel" type="button" onclick="goBack()">
				</div>
			</div>
			<div class="stepIntro"></div>
			<table class="formTable">
				<colgroup>
					<col class="labelCol">
					<col class="inputCol">
					<col class="paddingCol">
				</colgroup>
				<tbody>
					<tr class="rowIntro">
						<td colspan="3"><span class="dialog-info"> Originating information <br></span></td>
					</tr>
					<tr id="originatingDestinationCodeRow" class="row">
						<td width="30%">
						       <label> <span class="requiredfield">*</span>Originating-Destination code: </label></td>
						<td class="inputField" width="20%">
							<div>
								<spring:select path="originatingDestinationCode">
									<spring:option selected="selected" value=""/>
									<c:forEach var="item" items="${destinations}">
										<spring:option value="${item.destinationCode}" label="${item.destinationCode} ${item.destinationDescription }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>						
						<td width="50%">
						   <spring:errors path="originatingDestinationCode"/>
						</td>
					</tr>
					<tr id="originatingSatelliteCodeRow" class="row">
						<td>
							<label> <span class="requiredfield">*</span>Originating-Satellite code (1 char):</label>
						</td>
						<td class="inputField">
							<div>
								<spring:select path="originatingSatelliteCode">
								 	<spring:option selected="selected" value=""/>
									 <c:forEach var="item" items="${satelliteCodes}">
									 	<spring:option value="${item.key}" label="${item.value}"/>
									 </c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="originatingSatelliteCode"/></td>
					</tr>
					<tr class="rowIntro">
						<td colspan="3"><span class="dialog-info"> Terminating information <br></span></td>
					</tr>
					<tr id="terminatingDestinationCodeRow" class="row">
						<td><label> <span class="requiredfield">*</span>Terminating-Destination code:</label></td>
						<td class="inputField">
							<div>
								<spring:select path="terminatingDestinationCode">
									<spring:option selected="selected" value=""/>
									<c:forEach var="item" items="${destinations}">
										<spring:option value="${item.destinationCode}" label="${item.destinationCode} ${item.destinationDescription }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="terminatingDestinationCode"/> </td>
					</tr>
					<tr id="terminatingSatelliteCodeRow" class="row">
						<td><label> <span class="requiredfield">*</span>Terminating-Satellite code (1 char):</label></td>
						<td class="inputField">
							<div>
								<spring:select path="terminatingSatelliteCode">
									<spring:option selected="selected" value=""/>
									 <c:forEach var="item" items="${satelliteCodes}">
									 	<spring:option value="${item.key}" label="${item.value}"/>
									 </c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="terminatingSatelliteCode"/></td>
					</tr>
					<tr class="rowIntro">
						<td colspan="3"><span class="dialog-info"> Primary fields <br>
						</span></td>
					</tr>
					<tr id="weekDefinitionCodeRow" class="row">
						<td><label> <span class="requiredfield">*</span>Week definition code (5 chars):</label></td>
						<td class="inputField">
							<div>
								<spring:select path="weekDefinitionCode">
								    <c:forEach var="item" items="${ weekDefinitionCodes}">
								    	<spring:option value="${item.key }" label="${item.value}"/>
								    </c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="weekDefinitionCode"/></td>
					</tr>
					<tr id="ratePeriodSequenceNumber" class="row">
						<td><label> <span class="requiredfield">*</span>Rate period sequence number (4 chars):</label></td>
						<td class="inputField">
							<div>
								<fmt:formatNumber type="number" var="ratePeriodSequenceNumberString" value="${createdToolPlan.ratePeriodSequenceNumber}" pattern="0000"></fmt:formatNumber>
								<spring:input path="ratePeriodSequenceNumber" value="${ ratePeriodSequenceNumberString}" maxlength="4"/>
							</div>
						</td>
						<td><spring:errors path="ratePeriodSequenceNumber"/></td>
					</tr>
					<tr id="rateCapSequenceNumberRow" class="row">
						<td><label> <span class="requiredfield">*</span>Rate cap sequence number:</label></td>
						<td class="inputField">
							<div>
								<spring:input path="rateCapSequenceNumber"/>
							</div>
						</td>
						<td><spring:errors path="rateCapSequenceNumber"/></td>
					</tr>
					<tr id="typeOfCallRow" class="row">
						<td><label> <span class="requiredfield">*</span>Type of call (1 char):</label></td>
						<td class="inputField">
							<div>
								<spring:select path="typeOfCall">
								    <c:forEach var="item" items="${typeOfCalls}">
								    	<spring:option value="${item.key}" label="${item.value}"/>
								    </c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="typeOfCall"/></td>
					</tr>
					<tr id="speacialTypeOfCallRow" class="row">
						<td><label> <span class="requiredfield">*</span>Special	call type Code (4 chars):</label></td>
						<td class="inputField">
							<div>
								<spring:select	path="speacialTypeOfCall">
									<spring:option selected="selected" value=""/>
									<c:forEach var="item" items="${specialTypeOfCalls}">
										<spring:option value="${item.key}" label="${item.value}"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="speacialTypeOfCall"/></td>
					</tr>
					<tr id="tableEntryEffectiveDate" class="row">
						<td><label> <span class="requiredfield">*</span>Table entry effective date (YYYY-mm-dd):</label></td>
						<td class="inputField">
							<div>
								<fmt:formatDate value="${createdToolPlan.tableEntryEffectiveDate}" var="tableEntryEffectiveDateString" pattern="yyyy-MM-dd" />
								<spring:input path="tableEntryEffectiveDate" value="${tableEntryEffectiveDateString}" maxlength="10"/>
							</div>
						</td>
						<td><spring:errors path="tableEntryEffectiveDate"/></td>
					</tr>
					<tr id="tableEntryExpirationDate" class="row">
						<td><label> <span class="requiredfield">*</span>Table entry expiration date (YYYY-mm-dd):</label></td>
						<td class="inputField">
							<div>
								<fmt:formatDate value="${createdToolPlan.tableEntryExpirationDate}" var="tableEntryExpirationDateString" pattern="yyyy-MM-dd" />
								<spring:input path="tableEntryExpirationDate" value="${tableEntryExpirationDateString}" maxlength="10"/>
							</div>
						</td>
						<td><spring:errors path="tableEntryExpirationDate"/></td>
					</tr>

					<tr class="rowIntro">
						<td colspan="3"><span class="dialog-info">Non-key fields</span></td>
					</tr>
					<tr id="customerSpecificRatingCodeRow" class="row">
						<td><label> Customer specific rating code:</label></td>
						<td class="inputField">
							<div>
								<spring:select path="customerSpecificRatingCode">
									<spring:option selected="selected" value=""/>
									<c:forEach var="item" items="${customerSpecificRatingCodes}">
										<spring:option value="${ item.key }" label="${ item.value }"/>
									</c:forEach>
								</spring:select>
								<spring:input path="customerSpecificRatingCodeNew" maxlength="8"/>
							</div>
						</td>
						<td>
						  <spring:errors path="customerSpecificRatingCode"/>
						</td>
					</tr>
					<tr id="rateTypeRow" class="row">
						<td><label> Rate type (max 1 char): </label></td>
						<td class="inputField">
							<div>
								<spring:select path="rateType">
									<c:forEach var="item" items="${ rateType}">
										<spring:option value="${item.key }" label="${item.value }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="rateType"/></td>
					</tr>
					<tr id="rateRow" class="row">
						<td><label>Rate: </label></td>
						<td class="inputField">
							<div>
								<fmt:formatNumber type="number" pattern="0.00" var="rateString" value="${createdToolPlan.rate}"/>							
								<spring:input path="rate" value="${rateString}" maxlength="50" />
							</div>
						</td>
						<td><spring:errors path="rate"/></td>
					</tr>
					<tr id="rateTimeUnitRow" class="row">
						<td><label> Rate time unit: </label></td>
						<td class="inputField">
							<div>
								<spring:select path="rateTimeUnit">
									<spring:option value=""/>
									<c:forEach var="item" items="${rateTimeUnit }">
										<spring:option value="${item.key }" label="${item.value }"/>
									</c:forEach>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="rateTimeUnit"/></td>
					</tr>
					<tr id="messageLevelPercentDiscountRow" class="row">
						<td><label> Message level percent discount: </label></td>
						<td class="inputField">
							<div>
								<fmt:formatNumber var="messageLevelPercentDiscountString" value="${createdToolPlan.messageLevelPercentDiscount}" pattern="0.0"/>
								<spring:input path="messageLevelPercentDiscount" value="${messageLevelPercentDiscountString}" maxlength="50"/>
							</div>
						</td>
						<td><spring:errors path="messageLevelPercentDiscount"/></td>
					</tr>
					<tr id="capThresholdCodeRow" class="row">
						<td><label> Cap threshold code: </label></td>
						<td class="inputField">
							<div>
								<spring:select path="capThresholdCode">
									<spring:option selected="selected" value=""/>
									<c:forEach var="item" items="${capThresholdCode}">
										<spring:option value="${item.key }" label="${item.value}"/>
									</c:forEach>
								</spring:select>
								 &nbsp; 
								 <spring:input path="capThresholdCodeNew" maxlength="5"/>
							</div>
						</td>
						<td><spring:errors path="capThresholdCode"/></td>
					</tr>
					<tr id="minimumTimeRow" class="row">
						<td><label> Minimum time: </label></td>
						<td class="inputField">
							<div>
								<spring:input path="minimumTime" maxlength="10"/>
							</div>
						</td>
						<td><spring:errors path="minimumTime"/></td>
					</tr>
					<tr id="incrementTimeRow" class="row">
						<td><label> Increment time: </label></td>
						<td class="inputField">
							<div>
								<spring:input path="incrementTime" maxlength="10"/>
							</div>
						</td>
						<td><spring:errors path="incrementTime"/></td>
					</tr>
					<tr id="incrementTimeUnitRow" class="row">
						<td><label> Increment time unit: </label></td>
						<td class="inputField">
							<div>
								<spring:select path="incrementTimeUnit">
									<spring:option value=""/>
									<spring:option selected="selected" value="S" label="S"/>
									<spring:option value="M" label="M"/>
								</spring:select>
							</div>
						</td>
						<td><spring:errors path="incrementTimeUnit"/></td>
					</tr>
					<tr id="rateValidationIndicatorRow" class="row">
						<td><label> Rate validation indicator: </label></td>
						<td class="inputField">
							<div>
								<label>2</label>
							</div>
						</td>
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
var formId = '#toolPlanForm';
$(document).ready(function () {
	//$(formId).validationEngine({'binded':false});
	//$(formId).validationEngine().css({border : "21px solid #000"});
	$('#btnCancel').click(function(){
	});
});

function saveToolPlanCallback(){
	SEVENJ.redirect("forwardToViewPricePlan.htm");
}

function goBack(){
	SEVENJ.redirect("forwardToViewPricePlan.htm");
}


</script>