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
		<form id="searchAdditionalPricePlanForm" name="searchAdditionalPricePlanForm">
			<div class="introText">
				<p>Search Additional Price Plan Info:</p>
			</div>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="Search" type="button" onclick="btnSearchADD()"> 
					<input class="formButton" value="Cancel" type="button" onclick="goBack()">
					 <span id="showExportToExcel">
					 	<input class='formButton' value='Export To Excel' type='button' id='exportToExcel'>
					 </span>
				</div>
			</div>
			<ul>
				<li>
					<span>Price Plan Code:</span> 
					<input id="pricePlanCode" type="text" name="pricePlanCode" style="width: 170px" maxlength="4">
				</li>
				<li>
					<span>Price Plan Name:</span> 
					<input id="pricePlanName" type="text" name="pricePlanName" style="width: 170px" maxlength="256">
				</li>
				<li>
					<span>Plan Type:</span> 
					<select name="planType" id="planType">
						<option value="-1" selected="selected">All</option>
					    <c:forEach var="item" items="${ planTypes }">
							<option value="${item.id }">${item.description }</option>
						</c:forEach>	
					</select>
				</li>
				<li>
					<span>Account Level:</span> 
					<select name="accountLevel" id="accountLevel">
						<option value="-1" selected="selected">All</option>
					    <c:forEach var="item" items="${ generals }">
							<option value="${item.id }">${item.description }</option>
						</c:forEach>		
					</select>
				</li>
				<li>
					<span>Line Level:</span> 
					<select name="lineLevel" id="lineLevel">
						<option value="-1" selected="selected">All</option>
					    <c:forEach var="item" items="${ generals }">
							<option value="${item.id }">${item.description }</option>
						</c:forEach>		
					</select>
				</li>
				<li>
					<span>PriorityCode:</span> 
					<select name="prioprityCode" id="prioprityCode">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ priorityCodes}">
							<option value="${item.id }">${item.description }</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>USOC:</span> 
					<input name="usoc" id="usoc" type="text" maxlength="256">
				</li>
				<li>
					<span>Tool Pricing Plan:</span> 
					<select name="isToolPricingPlan" id="isToolPricingPlan">
						<option value="-1" selected="selected">All</option>
					    <c:forEach var="item" items="${ generals }">
							<option value="${item.id }">${item.description }</option>
						</c:forEach>
						<c:forEach var="item" items="${ isToolPricingPlans}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>No Overseas Destinations:</span> 
					<select name="noOverseaDest" id="noOverseaDest">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${ noOverseaDest}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Top 28 Overseas Destinations ONLY:</span> 
					<select name="t28OverseaDest" id="t28OverseaDest">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Top 28 &amp; 29+ Overseas Destinations:</span> 
					<select name="t29OverseaDest" id="t29OverseaDest">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${t29OverseaDests}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Block of Time :</span> 
					<select name="blockOfTime" id="blockOfTime">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${blockOfTimes}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Dollar CAP:</span> 
					<select name="dollarCap" id="dollarCap">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${dollarCaps}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				
				<li>
					<span>Bill Minium:</span> 
					<select name="billMinium" id="billMinium">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${billMiniums}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Quantity of BlockS:</span> 
					<select name="quantity" id="quantity">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${quantitys}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Short Haul Rule:</span> 
					<select name="shortHaulRule" id="shortHaulRule">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Cost Comparison Rule:</span> 
					<select name="costCompareOnRule" id="costCompareOnRule">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Calling Card Surchage:</span> 
					<select name="withSurcharges" id="withSurcharges">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Multiple Rate Schedules:</span> 
					<select name="multiRateSchedule" id="multiRateSchedule">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Airtime:</span> 
					<select name="airTime" id="airTime">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${airTimes}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Volume Discount:</span> 
					<select name="volumeDiscount" id="volumeDiscount">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Contract Discount:</span> 
					<select name="contractDiscount" id="contractDiscount">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>60/60 increments ONLY:</span> 
					<select name="increments60" id="increments60">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${increments60s}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>60/60 or 30/6 increments:</span> 
					<select name="increments60or30" id="increments60or30">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${increments60or30s}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>30/6 increments:</span> 
					<select name="increments30" id="increments30">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${increments30s}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>10/10 increments:</span> 
					<select name="increments10" id="increments10">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${increments10s}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				
				<li>
					<span>1/1 increments:</span> 
					<select name="increments1" id="increments1">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
						<c:forEach var="item" items="${increments1s}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Peak off Peak:</span> 
					<select name="peakOffPeak" id="peakOffPeak">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>24/7 Time Frame:</span> 
					<select name="is247" id="is247">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>TDH1 Handicap Discount:</span> 
					<select name="thd1" id="thd1">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<span>Provincial Ontario Only:</span> 
					<select name="provincialOntarioOnly" id="provincialOntarioOnly">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li><li>
					<span>Provincial Quebec Only:</span> 
					<select name="provincialQuebecOnly" id="provincialQuebecOnly">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ generals}">
							<option value="${item.id }">${item.description}</option>
						</c:forEach>
					</select>
				</li>
			</ul>
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" value="Search" type="button" onclick="btnSearchADD()">
					<input class="formButton" value="Cancel" type="button" onclick="goBack()">
					<span id="showExportToExcel1">
					 	<input class='formButton' value='Export To Excel' type='button' id='exportToExcel1'>
					 </span>
				</div>
			</div>
		</form>
	</div>
</div>
<div id="additionalPricePlanTable" class="jtable-main-container"></div>

<script type="text/javascript">
	$("#showExportToExcel").hide();
    $("#showExportToExcel1").hide();
	function clickExport(){
		if (confirm("Do you want to generate this report to excel format?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': 'exportToExcelADDPricePlanSearch.htm?' + $('#searchAdditionalPricePlanForm').serialize() }); 
			$('#lnkDwnldLnk')[0].click();    
			document.body.removeChild(link);
	    }
	} 
	$('#exportToExcel').click(function() {
		clickExport();
	}); 
	
	$('#exportToExcel1').click(function() {
		clickExport();
	}); 
	
	function btnSearchADD(){
		var searchAdditionalPPId ="#additionalPricePlanTable";
		setupAdditionalPPTable(searchAdditionalPPId);
		$(searchAdditionalPPId).jtable('reload');
	}
	function setupAdditionalPPTable(searchAdditionalPPId)
    {
		$(searchAdditionalPPId).jtable({
			title: '',
		    paging : true,
			pagSize : 50,
			sorting: true,
            multiSorting: true,
            defaultSorting: 'PricePlanName ASC',
		    actions: {
		    	listAction: function (postData, jtParams) {
                    console.log("Loading from custom function...");
                    return $.Deferred(function ($dfd) {
                        $.ajax({
                            url: 'searchADPricePlan.htm?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
                            type: 'POST',
                            dataType: 'json',
                            data: $('#searchAdditionalPricePlanForm').serialize(),
                            success: function (data) {
                            	if(data.Result == RESULT_OK){
                            		$("#showExportToExcel").show();
                            		$("#showExportToExcel1").show();
                            		//document.getElementById('showExportToExcel').style.display = 'block';
	                            	//document.getElementById("showExportToExcel").innerHTML = "<input class='formButton' value='Export To Excel' type='button' id='exportToExcel' name='exportToExcel'>";
	                            	//document.getElementById("showExportToExcel1").innerHTML = "<input class='formButton' value='Export To Excel' type='button' id='exportToExcel1' name='exportToExcel1'>";
                            	}else{
                            	}
                                $dfd.resolve(data);
                            },
                            error: function () {
                                alert('NONONO');
                                $dfd.reject();
                            }
                        });
                    });
		    	}
		    },
		    fields: {
	        	checkOutUser:{
	        		title: '',
	        		display: function(data){
	        			return '<a href="editAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '"><img src="images/checked_in.gif" class="state" alt="Edit" /></a>';
	        		},
	        		width: '1%',
	        		sorting: false
	        	},
	            pricePlanName: {
	                title: 'Price Plan Name',
	                display: function(data){
	                	return '<a href="viewAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '&selectedTab=1">' + data.record.PricePlanName + '</a>';
	                },
	                width: '20%',
	                sorting: true
	            },
	            description: {
	                title: 'Description',
	                edit: false,
	                display: function(data){
	                	var $linkDes = $('<a href="#">Description</a>');
	                   	$linkDes.click(function(){
	                   		window.open("viewDescriptionAdditionalPricePlan.htm?pricePlanCode=" + data.record.PricePlanCode,'_blank','height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
	                   	});
	                   	return $linkDes;
	                },
	                width: '8%',
	                sorting: false
	            },
	            pricePlanCode: {
	                key: true,
	                edit: false,
	                title: 'Price Plan Code',
	                display: function (data) {
	                	return data.record.PricePlanCode;
	                },
	                sorting: true
	            },
	            accountLevel: {
	                title: 'Account Level',
	                width: '11%',
	                display: function (data) {
	                	return data.record.AccountLevel.value;
	                },
	                sorting: true
	            },
	            lineLevel: {
	                title: 'Line Level',
	                width: '8%',
	                display: function (data) {
	                	return data.record.LineLevel.value;
	                },
	                sorting: true
	            },
	            prioprityCode: {
	                title: 'Prioprity Code',
	                width: '10%',
	                display: function (data) {
	                	return data.record.PrioprityCode.value;
	                },
	                sorting: true
	            },
	            usoc: {
	            	 title: 'USOC',
	            	 display: function (data) {
	                	return data.record.Usoc;
	                 },
	                 width: '20%',
	                 sorting: true
	            },
	            isToolPricingPlan: {
	                title: 'Tool',
	                display: function (data) {
	                	return data.record.IsToolPricingPlan.value;
	                },
	                sorting: true
	            },
			}
	    });
	    
    }
	function goBack() {
		SEVENJ.redirect("getAdditionalPricePlanList.htm");
	} 
	</script>