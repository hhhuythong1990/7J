<div id="portlet_priceplan" class="bell-window  ">
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
			<form id="additionalPricePlanForm" name="additionalPricePlanForm" method="post">
				<div class="introText">
					<p>View  Additional Price Plan Infos</p>
					<a href="#" onclick="goBack()">Back to Additional Price Plan Info</a>
				</div>
				<%-- <div class="upperButtonBar">
					<div class="buttonBar">
						<input class="formButton" value="Edit" type="button"
							onclick="location='editAdditionalPricePlan.htm?pricePlanCode=${viewAdditionalPricePlan.pricePlanCode}';"> <input class="formButton"
							value="Back" type="button" onclick="goBack()">
					</div>
				</div> --%>
				
				<table class="formTable">
					<colgroup>
						<col class="labelCol">
						<col class="inputCol">
						<col class="paddingCol">
					</colgroup>
					<tbody>
						<tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Pricing Plan
									Information <br>
							</span></td>
						</tr>
						<tr class="row">
							<td><label> Pricing Plan Code:
							</label></td>
							<td class="inputField">
								<div>
								<label id="pricePlanCode">${viewAdditionalPricePlan.pricePlanCode}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr id="PricingPlanName" class="row">
							<td width="40%"><label>Pricing Plan Name:
							</label></td>
							<td class="inputField">
								<div>
								<label id="pricePlanName">${viewAdditionalPricePlan.pricePlanName}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Description:
							</label></td>
							<td class="inputField">
								<div>
									<a href="#" onclick="showDescription('viewDescriptionAdditionalPricePlan.htm?pricePlanCode=${viewAdditionalPricePlan.pricePlanCode}')">Description</a>
								</div>
							</td>
							<td></td>
						</tr>
						<tr id="IsAccount" class="row">
							<td><label>Account Level:
							</label></td>
							<td class="inputField">
								<div>
								<label id="accountLevel">${dataList.get(viewAdditionalPricePlan.accountLevel)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr id="priorityCode" class="row">
							<td><label>PriorityCode:
							</label></td>
							<td class="inputField">
								<div>
								<label id="prioprityCode">${dataList.get(viewAdditionalPricePlan.priorityCode)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label> USOC: </label></td>
							<td class="inputField">
								<div>
									<label id="usoc">${viewAdditionalPricePlan.usoc}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Tool Pricing Plan:
							</label></td>
							<td class="inputField">
								<div>
								<label id="isToolPricingPlan">${dataList.get(viewAdditionalPricePlan.isToolPricingPlan)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<%-- <tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Overseas
									destinations information <br>
							</span></td>
						</tr>
						<tr class="row">
							<td><label> No
									Overseas Destinations:
							</label></td>
							<td class="inputField">
								<div>
								<label id="noOverseaDest">${dataList.get(viewAdditionalPricePlan.noOverseaDest)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Top 28 Overseas Destinations ONLY:
							</label></td>
							<td class="inputField">
								<div>
								<label id="top28OverseaDest">${dataList.get(viewAdditionalPricePlan.top28OverseaDest)}</label>
								</div>
							</td>
							<td></td>
						</tr> --%>
						<tr class="row">
							<td><label>Top 28 &amp; 29+ Overseas Destinations:
							</label></td>
							<td class="inputField">
								<div>
								<label id="t29OverseaDest">${dataList.get(viewAdditionalPricePlan.t29OverseaDest)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<%-- <tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Block of
									time <br>
							</span></td>
						</tr>
						<tr class="row">
							<td><label>Block
									of time:
							</label></td>
							<td class="inputField">
								<div>
								<label id="blockOfTime">${dataList.get(viewAdditionalPricePlan.blockOfTime)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Dollar
									CAP:
							</label></td>
							<td class="inputField">
								<div>
								<label id="dollarCap">${dataList.get(viewAdditionalPricePlan.dollarCap)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Bill
									Minium:
							</label></td>
							<td class="inputField">
								<div>
								<label id="billMinium">${dataList.get(viewAdditionalPricePlan.billMinium)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label> Quantity:
							</label></td>
							<td class="inputField">
								<div>
								<label id="quantity">${dataList.get(viewAdditionalPricePlan.quantity)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Rule <br>
							</span></td>
						</tr>
						<tr class="row">
							<td><label> Short
									Haul Rule:
							</label></td>
							<td class="inputField">
								<div>
								<label id="shortHaulRule">${dataList.get(viewAdditionalPricePlan.shortHaulRule)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Cost
									Comparison Rule:
							</label></td>
							<td class="inputField">
								<div>
								<label id="costCompareOnRule">${dataList.get(viewAdditionalPricePlan.costCompareOnRule)}</label>
								</div>
							</td>
							<td></td>
						</tr>--%>
						<tr class="row">
							<td><label> Calling Card Surcharges:
							</label></td>
							<td class="inputField">
								<div>
								<label id="withSurcharges">${dataList.get(viewAdditionalPricePlan.withSurcharges)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<%--<tr class="row">
							<td><label>Multiple
									Rate Schedules:
							</label></td>
							<td class="inputField">
								<div>
								<label id="multiRateSchedule">${dataList.get(viewAdditionalPricePlan.multiRateSchedule)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>Airtime:
							</label></td>
							<td class="inputField">
								<div>
								<label id="airTime">${dataList.get(viewAdditionalPricePlan.airTime)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Discount <br>
							</span></td>
						</tr>
						<tr class="row">
							<td><label>Volume
									Discount:
							</label></td>
							<td class="inputField">
								<div>
								<label id="volumeDiscount">${dataList.get(viewAdditionalPricePlan.volumeDiscount)}</label>
								</div>
							</td>
							<td></td>
						</tr> --%>
						<tr class="row">
							<td><label>Contract Discount:
							</label></td>
							<td class="inputField">
								<div>
									<label id="contractDiscount">${dataList.get(viewAdditionalPricePlan.contractDiscount)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<!-- <tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Increments
									Information <br>
							</span></td>
						</tr> -->
						<tr class="row">
							<td><label>60/60 Increments ONLY:
							</label></td>
							<td class="inputField">
								<div>
								<label id="increments60">${dataList.get(viewAdditionalPricePlan.increments60)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<%-- <tr class="row">
							<td><label>60/60
									or 30/6 increments:
							</label></td>
							<td class="inputField">
								<div>
								<label id="increments60or30">${dataList.get(viewAdditionalPricePlan.increments60or30)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>30/6
									increments:
							</label></td>
							<td class="inputField">
								<div>
								<label id="increments30">${dataList.get(viewAdditionalPricePlan.increments30)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>10/10
									increments:
							</label></td>
							<td class="inputField">
								<div>
								<label id="increments10">${dataList.get(viewAdditionalPricePlan.increments10)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>1/1
									increments:
							</label></td>
							<td class="inputField">
								<div>
								<label id="increments1">${dataList.get(viewAdditionalPricePlan.increments1)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="rowIntro">
							<td colspan="3"><span class="dialog-info">Peak off
									Peak <br>
							</span></td>
						</tr>
						<tr class="row">
							<td><label>Peak
									Off-Peak:
							</label></td>
							<td class="inputField">
								<div>
								<label id="peakOffPeak">${dataList.get(viewAdditionalPricePlan.peakOffPeak)}</label>
								</div>
							</td>
							<td></td>
						</tr> --%>
						<tr class="row">
							<td><label>24/7 Time Frame:
							</label></td>
							<td class="inputField">
								<div>
								<label id="is247">${dataList.get(viewAdditionalPricePlan.is247)}</label>
								</div>
							</td>
							<td></td>
						</tr>
						<tr class="row">
							<td><label>TDH1 - Handicap Discount:
							</label></td>
							<td class="inputField">
								<div>
								<label id="thd1">${dataList.get(viewAdditionalPricePlan.thd1)}</label>
								</div>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
				<%-- <div class="upperButtonBar">
					<div class="buttonBar">
						<input class="formButton" value="Edit" type="button" onclick="location='editAdditionalPricePlan.htm?pricePlanCode=${viewAdditionalPricePlan.pricePlanCode}';">
						 <input class="formButton" value="Back" type="button" onclick="goBack()">
					</div>
				</div> --%>
			</form>
		</div>
	</div>
</div>

<script>
	function goBack() {
		SEVENJ.redirect("getAdditionalPricePlanList.htm");
	} 
	
	function showDescription(url){
		window.open(url,'_blank','height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
	}
</script>