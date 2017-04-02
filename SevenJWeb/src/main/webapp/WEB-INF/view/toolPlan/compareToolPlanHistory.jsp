<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld" %>
<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Compare Price Plan Record</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
<div class="contenttable">
	<div class="introText">
		<p>Compare with current Tool Plan</p>
	</div>
	<div class="upperButtonBar">
		<div class="buttonBar">
			<input type="button" class="formButton" value="Back" onclick="SEVENJ.back();"/>
		</div>
	</div>
	<table class="formTable" id="compareHistory">
			<c:if test="${oldToolPlan.originatingDestinationCode != currentToolPlan.originatingDestinationCode}">
				<tr id="originatingDestinationCodeRow" class="row">
					<td><label> Originating-Destination Code: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.originatingDestinationCode}</span>
							<label> change to </label>
							<span>${currentToolPlan.originatingDestinationCode}</span>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.originatingSatelliteCode != currentToolPlan.originatingSatelliteCode}">
				<tr id="originatingSatelliteCodeRow" class="row">
					<td><label> Originating-Satellite Code: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.originatingSatelliteCode}</span>
							<label> change to </label>
							<span>${currentToolPlan.originatingSatelliteCode}</span>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.terminatingDestinationCode != currentToolPlan.terminatingDestinationCode}">
				<tr id="terminatingDestinationCodeRow" class="row">
					<td><label> Terminating-Destination Code: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.terminatingDestinationCode}</span>
						<label> change to </label>
						<span>${currentToolPlan.terminatingDestinationCode}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.terminatingSatelliteCode != currentToolPlan.terminatingSatelliteCode}">
				<tr id="terminatingSatelliteCodeRow" class="row">
					<td><label> Terminating-Satellite Code: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.terminatingSatelliteCode}</span>
							<label> change to </label>
							<span>${currentToolPlan.terminatingSatelliteCode}</span>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.weekDefinitionCode != currentToolPlan.weekDefinitionCode}">
				<tr id="weekDefinitionCodeRow" class="row">
					<td><label> Week definition Code: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.weekDefinitionCode}</span>
							<label> change to </label>
							<span>${currentToolPlan.weekDefinitionCode}</span>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.rateCapSequenceNumber != currentToolPlan.rateCapSequenceNumber}">
				<tr id="rateCapSequenceNumberRow" class="row">
					<td><label> Rate period sequence number: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.rateCapSequenceNumber}</span>
							<label> change to </label>
							<span>${currentToolPlan.rateCapSequenceNumber}</span>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.typeOfCall != currentToolPlan.typeOfCall}">
				<tr id="typeOfCallRow" class="row">
					<td><label> Type of call: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.typeOfCall}</span>
							<label> change to </label>
							<span>${currentToolPlan.typeOfCall}</span>
						</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.speacialTypeOfCall != currentToolPlan.speacialTypeOfCall}">
				<tr id="speacialTypeOfCallRow" class="row">
					<td><label> Special call type code: </label></td>
					<td class="inputField">
						<div>
							<span>${oldToolPlan.speacialTypeOfCall}</span>
							<label> change to </label>
							<span>${currentToolPlan.speacialTypeOfCall}</span>
						</div>				
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.tableEntryEffectiveDate != currentToolPlan.tableEntryEffectiveDate}">
				<tr id="tableEntryEffectiveDate" class="row">
					<td><label> Table entry effective date (YYYY-mm-dd): </label></td>
					<td class="inputField">
					<div>
						<span><fmt:formatDate value="${oldToolPlan.tableEntryEffectiveDate}" pattern="yyyy-MM-dd"/></span>
						<label> change to </label> 
						<span><fmt:formatDate value="${currentToolPlan.tableEntryEffectiveDate}" pattern="yyyy-MM-dd"/></span>
					</div> 
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.tableEntryExpirationDate != currentToolPlan.tableEntryExpirationDate}">
				<tr id="tableEntryExpirationDate" class="row">
					<td><label> Table entry expiratiom date (YYYY-mm-dd): </label></td>
					<td class="inputField">
					<div>
						<span><fmt:formatDate value="${oldToolPlan.tableEntryExpirationDate}" pattern="yyyy-MM-dd"/></span>
						<label> change to </label>
						<span><fmt:formatDate value="${currentToolPlan.tableEntryExpirationDate}" pattern="yyyy-MM-dd"/></span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.customerSpecificRatingCode != currentToolPlan.customerSpecificRatingCode}">
				<tr id="customerSpecificRatingCodeRow" class="row">
					<td><label> Customer specific rating code: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.customerSpecificRatingCode}</span>
						<label> change to </label>
						<span>${currentToolPlan.customerSpecificRatingCode}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.rateType != currentToolPlan.rateType}">
				<tr id="rateTypeRow" class="row">
					<td><label> Rate type: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.rateType}</span>
						<label> change to </label>
						<span>${currentToolPlan.rateType}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.rate != currentToolPlan.rate}"> 
				<tr id="rateRow" class="row">
					<td><label> Rate: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.rate}</span>
						<label> change to </label>
						<span>${currentToolPlan.rate}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.rateTimeUnit != currentToolPlan.rateTimeUnit}">
				<tr id="rateTimeUnitRow" class="row">
					<td><label> Rate time unit: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.rateTimeUnit}</span>
						<label> change to </label>
						<span>${currentToolPlan.rateTimeUnit}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.messageLevelPercentDiscount != currentToolPlan.messageLevelPercentDiscount}">
				<tr id="messageLevelPercentDiscountRow" class="row">
					<td><label> Message Level Percent Discount: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.messageLevelPercentDiscount}</span>
						<label> change to </label>
						<span>${currentToolPlan.messageLevelPercentDiscount}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.capThresholdCode != currentToolPlan.capThresholdCode}">
				<tr id="capThresholdCodeRow" class="row">
					<td><label> Cap threshold code: </label></td>
					<td class="inputField">
					<div>
						<span>${pageInput.oldToolPlan.capThresholdCode}</span>
						<label> change to </label>
						<span>${pageInput.currentToolPlan.capThresholdCode}</span>
					</div>
					</td>
					<td></td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.minimumTime != currentToolPlan.minimumTime}">
				<tr id="minimumTimeRow" class="row">
					<td><label> Minimum time: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.minimumTime}</span>
						<label> change to </label>
						<span>${currentToolPlan.minimumTime}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.incrementTime != currentToolPlan.incrementTime}">
				<tr id="incrementTimeRow" class="row">
					<td><label> Increment time: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.incrementTime}</span>
						<label> change to </label>
						<span>${currentToolPlan.incrementTime}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.incrementTimeUnit != currentToolPlan.incrementTimeUnit}">
				<tr id="incrementTimeUnitRow" class="row">
					<td><label> Increment time unit: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.incrementTimeUnit}</span>
						<label> change to </label>
						<span>${currentToolPlan.incrementTimeUnit}</span>
					</div>
					</td>
				</tr>
				</c:if>
				<c:if test="${oldToolPlan.rateValidationIndicator != currentToolPlan.rateValidationIndicator}">
				<tr id="rateValidationIndicatorRow" class="row">
					<td><label> Rate validation indicator: </label></td>
					<td class="inputField">
					<div>
						<span>${oldToolPlan.rateValidationIndicator}</span>
						<label> change to </label>
						<span>${currentToolPlan.rateValidationIndicator}</span>
					</div>
					</td>
				</tr>
				</c:if>
	</table>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var content = $.trim($('#compareHistory').html());
	if (content == '') {
		$('#compareHistory').html('<tr class="row"><td>There are no changes</td></tr>')
	}
});
</script>