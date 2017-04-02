<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld" %>
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
		<p>Edit tool plan for Price Plan code "${webSession.currentPricePlan.pricePlanCode}"</p>
	</div>
	<spring:form id="editToolPlanForm" name="editToolPlanForm" action="editToolPlan.htm" commandName="toolPlan">
	    <spring:hidden path="ID" value="${toolPlan.ID}"/>
	    <spring:hidden path="cbssStatus" value="${toolPlan.cbssStatus}"/>
		<div class="upperButtonBar">
			<div class="buttonBar">
				<input type="submit" class="formButton" value="OK" />
				<input type="button" class="formButton" value="Cancel" onclick="SEVENJ.gobackToViewPricePlan();"/>
			</div>
		</div>
		<div class="stepIntro">
			The following properties will be used on your tool plan.<br />
		</div>
		<div class="requiredfield">* Indicates required fields</div>
		<table class="formTable">
			<colgroup>
				<col class="labelCol" />
				<col class="inputCol" />
				<col class="paddingCol" />
			</colgroup>
			<tbody>
				<tr class="rowIntro">
					<td colspan="3">
						<span class="dialog-info"> Originating information <br /></span>
					</td>
				</tr>
				<tr id="originatingDestinationCodeRow" class="row">
					<td width="20%">
						<label> 
							<span class="requiredfield">*</span>Originating-Destination	code:
						</label>
					</td>
					<td class="inputField" width="20%">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="originatingDestinationCode">
										<spring:option value=""/>
										<c:forEach var="item" items="${destinations}">
										    <spring:option value="${item.destinationCode}" label="${item.destinationCode} ${item.destinationDescription }"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
								    <spring:hidden path="originatingDestinationCode"/>
									<span>${originatingDestination.destinationCode} ${originatingDestination.destinationDescription }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="originatingDestinationCode"/></td>
				</tr>
				<tr class="row">
					<td>
						<label> 
							<span class="requiredfield">*</span>Originating-Satellite code (1 char):
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="originatingSatelliteCode">
									 	<spring:option value=""/>
										 <c:forEach var="item" items="${satelliteCodes}">
										 	<spring:option value="${item.key}" label="${item.value}"/>
										 </c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="originatingSatelliteCode"/>
									<span>${toolPlan.originatingSatelliteCode}</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="originatingSatelliteCode"/></td>
				</tr>
				<tr class="rowIntro">
					<td colspan="2">
					   <span class="dialog-info"> Terminating information </span>
					</td>
				</tr>
				<tr class="row">
					<td>
					    <label>
					    	<span class="requiredfield">*</span>Terminating-Destination	code:
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select	path="terminatingDestinationCode">
										<spring:option value=""/>
										<c:forEach var="item" items="${destinations}">
											<spring:option value="${item.destinationCode}" label="${item.destinationCode} ${item.destinationDescription }"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="terminatingDestinationCode"/>
									<span>${ terminatingDestination.destinationCode } ${terminatingDestination.destinationDescription }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="terminatingDestinationCode"/></td>
				</tr>
				<tr class="row">
					<td>
						<label> 
						      <span class="requiredfield">*</span>Terminating-Satellite Code (1 char):
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="terminatingSatelliteCode">
										<spring:option value=""/>
										 <c:forEach var="item" items="${satelliteCodes}">
										 	<spring:option value="${item.key}" label="${item.value}"/>
										 </c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="terminatingSatelliteCode"/>
									<span>${ toolPlan.terminatingSatelliteCode }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
				<tr class="rowIntro">
					<td colspan="2"><span class="dialog-info"> Primary fields <br /></span></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span> Week definition code (5 chars):
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="weekDefinitionCode">
									    <c:forEach var="item" items="${ weekDefinitionCodes}">
									    	<spring:option value="${item.key }" label="${item.value}"/>
									    </c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="weekDefinitionCode"/>
									<span>${ toolPlan.weekDefinitionCode }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="weekDefinitionCode"/></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span>Rate period sequence number (4 chars):
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">									
									<spring:input path="ratePeriodSequenceNumber"/>
								</c:when>
								<c:otherwise>
									<spring:hidden path="ratePeriodSequenceNumber"/>
									<span>${ toolPlan.ratePeriodSequenceNumber }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="ratePeriodSequenceNumber"/></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span>Rate cap sequence number:
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">									
									<spring:input path="rateCapSequenceNumber"/>
								</c:when>
								<c:otherwise>
									<spring:hidden path="rateCapSequenceNumber"/>
									<span>${ toolPlan.rateCapSequenceNumber }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="rateCapSequenceNumber"/></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span>Type of	call (1 char):
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="typeOfCall">
									    <c:forEach var="item" items="${typeOfCalls}">
									    	<spring:option value="${item.key}" label="${item.value}"/>
									    </c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="typeOfCall"/>
									<span>${ toolPlan.typeOfCall }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="typeOfCall"/></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span>Special	call type code (4 chars):
						</label>
					</td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="speacialTypeOfCall">
										<spring:option value=""/>
										<c:forEach var="item" items="${specialTypeOfCalls}">
											<spring:option value="${item.key}" label="${item.value}"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="speacialTypeOfCall"/>
									<span>${ toolPlan.speacialTypeOfCall }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="speacialTypeOfCall"/></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span>Table entry effective date (YYYY-mm-dd):
						</label>
					</td>
					<td class="inputField">
						<div>
							<fmt:formatDate value="${toolPlan.tableEntryEffectiveDate}" var="tableEntryEffectiveDateString" pattern="yyyy-MM-dd" />
							<spring:input path="tableEntryEffectiveDate" value="${tableEntryEffectiveDateString}" maxlength="10"/>
						</div>
					</td>
					<td><spring:errors path="tableEntryEffectiveDate"/></td>
				</tr>
				<tr class="row">
					<td>
						<label>
							<span class="requiredfield">*</span>Table entry expiratiom date (YYYY-mm-dd):
						</label>
					</td>
					<td class="inputField">
						<div>
							<fmt:formatDate value="${toolPlan.tableEntryExpirationDate}" var="tableEntryExpirationDateString" pattern="yyyy-MM-dd" />
							<spring:input path="tableEntryExpirationDate" value="${tableEntryExpirationDateString}" maxlength="10"/>
						</div>
					</td>
				</tr>
	
				<tr class="rowIntro">
					<td colspan="2">
						<span class="dialog-info"> Non-key fields <br /></span>
					</td>
				</tr>
				<tr class="row">
					<td><label> Customer specific rating code: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="customerSpecificRatingCode">
										<spring:option value=""/>
										<c:forEach var="item" items="${customerSpecificRatingCodes}">
											<spring:option value="${ item.key }" label="${ item.value }"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="customerSpecificRatingCode"/>
									<span>${ toolPlan.customerSpecificRatingCode }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="customerSpecificRatingCode"/></td>
				</tr>
				<tr class="row">
					<td><label> Rate type (max 1 char): </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="rateType">
										<c:forEach var="item" items="${ rateType}">
											<spring:option value="${item.key }" label="${item.value }"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="rateType"/>
									<span>${ toolPlan.rateType }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="rateType"/></td>
				</tr>
				<tr class="row">
					<td><label> Rate: </label></td>
					<td class="inputField">
						<div>
							<fmt:formatNumber type="number" pattern="0.00" var="rateString" value="${toolPlan.rate}"/>							
							<spring:input path="rate" value="${rateString}" maxlength="50" />
						</div>
					</td>
					<td><spring:errors path="rate"/></td>
				</tr>
				<tr id="rateTimeUnitRow" class="row">
					<td><label> Rate time unit: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="rateTimeUnit">
										<spring:option value=""/>
										<c:forEach var="item" items="${rateTimeUnit }">
											<spring:option value="${item.key }" label="${item.value }"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="rateTimeUnit"/>
									<span>${ toolPlan.rateTimeUnit }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="rateTimeUnit"/></td>
				</tr>
				<tr class="row">
					<td><label> Message level percent discount: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<fmt:formatNumber var="messageLevelPercentDiscountString" value="${createdToolPlan.messageLevelPercentDiscount}" pattern="0.0"/>
									<spring:input path="messageLevelPercentDiscount" value="${messageLevelPercentDiscountString}" maxlength="50"/>
								</c:when>
								<c:otherwise>
									<spring:hidden path="messageLevelPercentDiscount"/>
									<span>${ toolPlan.messageLevelPercentDiscount }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="messageLevelPercentDiscount"/></td>
				</tr>
				<tr id="capThresholdCodeRow" class="row">
					<td><label> Cap threshold code: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${ toolPlan.cbssStatus == 7}">
									<spring:select path="capThresholdCode">
										<spring:option value=""/>
										<c:forEach var="item" items="${capThresholdCode}">
											<spring:option value="${item.key }" label="${item.value}"/>
										</c:forEach>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="capThresholdCode"/>
									<span>${ toolPlan.capThresholdCode }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="capThresholdCode"/></td>
				</tr>
				<tr class="row">
					<td><label> Minium time: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:input path="minimumTime" maxlength="10"/>
								</c:when>
								<c:otherwise>
									<spring:hidden path="minimumTime"/>
									<span>${ toolPlan.minimumTime }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="minimumTime"/></td>
				</tr>
				<tr class="row">
					<td><label> Increment time: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${ toolPlan.cbssStatus == 7}">
									<spring:input path="incrementTime" maxlength="10"/>
								</c:when>
								<c:otherwise>
									<spring:hidden path="incrementTime"/>
									<span>${ toolPlan.incrementTime }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><spring:errors path="incrementTime"/></td>
				</tr>
				<tr class="row">
					<td><label> Increment time unit: </label></td>
					<td class="inputField">
						<div>
							<c:choose>
								<c:when test="${toolPlan.cbssStatus == 7}">
									<spring:select path="incrementTimeUnit">
										<spring:option value=""/>
										<spring:option value="S" label="S"/>
										<spring:option value="M" label="M"/>
									</spring:select>
								</c:when>
								<c:otherwise>
									<spring:hidden path="incrementTimeUnit"/>
									<span>${ toolPlan.incrementTimeUnit }</span>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
				<tr class="row">
					<td><label> Rate validation indicator: </label></td>
					<td class="inputField">
						<div>
							<span>${ toolPlan.rateValidationIndicator }</span>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="upperButtonBar">
			<div class="buttonBar">
				<input type="submit" class="formButton" value="OK"/>
				<input type="button" class="formButton" value="Cancel" onclick="SEVENJ.gobackToViewPricePlan();"/>
			</div>
		</div>
	</spring:form>
</div>
</div>
<script type="text/javascript">
var formId = '#editToolPlanForm';

function success(){
	SEVENJ.redirect('viewPricePlan.htm?pricePlanCode=${webSession.currentPricePlan.pricePlanCode}&selectedTab=0');
}
</script>