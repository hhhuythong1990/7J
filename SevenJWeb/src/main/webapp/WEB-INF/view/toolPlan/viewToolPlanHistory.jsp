<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld" %>
<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>View Price Plan Record History</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
<div class="contenttable">
	<div class="introText">
		<p>View Price Plan Record History</p>
	</div>
	<form id="editToolPlanForm" name="editToolPlanForm">
		<div class="upperButtonBar">
			<div class="buttonBar">
				<input type="button" class="formButton" value="Back" onclick="SEVENJ.gobackToViewPricePlan();"/>
			</div>
		</div>
		<table class="formTable">
			<tbody>
				<tr class="rowIntro">
					<td colspan="2">
						<span class="dialog-info"> Originating information <br /></span>
					</td>
				</tr>
				<tr id="originatingDestinationCodeRow" class="row">
					<td width="20%">
						<span>Originating-Destination Code:</span>
					</td>
					<td width="20%">					
						<span>${toolPlan.originatingDestinationCode}</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						<span>Originating-Satellite Code (1 char):</span>
					</td>
					<td>
						<span>${toolPlan.originatingSatelliteCode}</span>
					</td>
				</tr>
				<tr class="rowIntro">
					<td colspan="2">
					   <span class="dialog-info"> Terminating information </span>
					</td>
				</tr>
				<tr class="row">
					<td>
					    Terminating-Destination	Code:
					</td>
					<td>
						<span>${ toolPlan.terminatingDestinationCode }</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Terminating-Satellite Code (1 char):
					</td>
					<td>
						<span>${ toolPlan.terminatingSatelliteCode }</span>
					</td>
				</tr>
				<tr class="rowIntro">
					<td colspan="2"><span class="dialog-info"> Primary fields</span></td>
				</tr>
				<tr class="row">
					<td>
						Week definition Code (5 chars):
					</td>
					<td>
						<span>${ toolPlan.weekDefinitionCode }</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Rate period sequence number:
					</td>
					<td>
						<span>${ toolPlan.ratePeriodSequenceNumber }</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Rate cap sequence number:
					</td>
					<td>
						<span>${ toolPlan.rateCapSequenceNumber }</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Type of	call (1 char):
					</td>
					<td>
						<span>${ toolPlan.typeOfCall }</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Special	call type code (4 chars):
					</td>
					<td>
						<span>${ toolPlan.speacialTypeOfCall }</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Table entry effective date (YYYY-mm-dd):
					</td>
					<td>
						<fmt:formatDate value="${toolPlan.tableEntryEffectiveDate}" var="tableEntryEffectiveDateValue" pattern="yyyy-MM-dd"/>
						<span>${tableEntryEffectiveDateValue}</span>
					</td>
				</tr>
				<tr class="row">
					<td>
						Table entry expiratiom date (YYYY-mm-dd):
					</td>
					<td>
					    <fmt:formatDate value="${toolPlan.tableEntryExpirationDate}" var="tableEntryExpirationDateValue" pattern="yyyy-MM-dd"/>
						<span>${tableEntryExpirationDateValue}</span>
					</td>
				</tr>
	
				<tr class="rowIntro">
					<td colspan="2">
						<span class="dialog-info"> Non-key fields <br /></span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Customer specific rating code: </label></td>
					<td>
						<span>${ toolPlan.customerSpecificRatingCode }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Rate type (max 1 char): </label></td>
					<td>
						<span>${ toolPlan.rateType }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Rate: </label></td>
					<td>
						<span>${toolPlan.rate}</span>
					</td>
				</tr>
				<tr id="rateTimeUnitRow" class="row">
					<td><label> Rate time unit: </label></td>
					<td>
						<span>${ toolPlan.rateTimeUnit }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Message Level Percent Discount: </label></td>
					<td>
						<span>${ toolPlan.messageLevelPercentDiscount }</span>
					</td>
				</tr>
				<tr id="capThresholdCodeRow" class="row">
					<td><label> Cap threshold code: </label></td>
					<td>
						<span>${ toolPlan.capThresholdCode }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Minimum time: </label></td>
					<td>
						<span>${ toolPlan.minimumTime }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Increment time: </label></td>
					<td>
						<span>${ toolPlan.incrementTime }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Increment time unit: </label></td>
					<td>
						<span>${ toolPlan.incrementTimeUnit }</span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Rate validation indicator: </label></td>
					<td>
						<span>${ toolPlan.rateValidationIndicator }</span>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="upperButtonBar">
			<div class="buttonBar">
				<input type="button" class="formButton" value="Back" onclick="SEVENJ.gobackToViewPricePlan();"/>
			</div>
		</div>
	</form>
</div>
</div>