<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Search Tool Plan Info</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div>
		Search by:
		
		<div>
		  <form id="searchForm" name="searchForm">
			<ul>
				<li>
					<span>Status:</span> 
					<select	name="pricePlanStatus">
							<option value="-1" selected="selected">All</option>
							<c:forEach var="item" items="${ toolPlanStatuses}">
							 	<option value="${item.key}">${item.value}</option>
							</c:forEach>					
					</select>
				</li>
				<li>
					<span>Originating Destination Code: </span> 
					<select name="originatingDestinationCode">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${destinations}">
							<option value="${item.destinationCode}">${item.destinationCode} ${item.destinationDescription }</option>
						</c:forEach>
					</select>
				</li>
		
				<li>
					<span>Terminating Satellite Code: </span> 
					<select	name="terminatingSatelliteCode">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ satelliteCodes}">
						 	<option value="${item.key}">${item.value}</option>
					     </c:forEach>
					</select>
				</li>
		
				<li>
					<span>Rate: </span> 
					<input type="text" name="rate" id="rate">
				</li>
		
				<li>
					<span>Rate Type: </span> 
					<select name="rateType">
							<option value="-1" selected="selected">All</option>
							<c:forEach var="item" items="${ rateType}">
						 	<option value="${item.key}">${item.value}</option>
					     </c:forEach>
					</select>
				</li>
		
				<li>
					<span>Week Definition Code: </span>
					<select name="weekDefinitionCode">
						<option value="-1" selected="selected">All</option>
						<c:forEach var="item" items="${ weekDefinitionCodes}">
						 	<option value="${item.key}">${item.value}</option>
					     </c:forEach>
					</select>
				</li>
		
				<li>
					<span>Rate Period Seq Number: </span> 
					<input type="text" name="ratePeriodSequenceNumber" id="ratePeriodSequenceNumber">
				</li>
		
				<li>
					<span>Type of Call: </span>
					<select name="typeOfCall">
							<option value="-1" selected="selected">All</option>
							<c:forEach var="item" items="${ typeOfCalls}">
						 		<option value="${item.key}">${item.value}</option>
					     	</c:forEach>
					</select>
				</li>
		
				<li>
					<span>Special Call Type Code: </span>
					<select name="speacialTypeOfCall">
							<option value="-1" selected="selected">All</option>
							<c:forEach var="item" items="${ specialTypeOfCalls}">
						 		<option value="${item.key}">${item.value}</option>
					     	</c:forEach>
					</select>
				</li>
		
				<li>
					<span>Rate Cap Seq Number: </span>
					<input type="text" name="rateCapSequenceNumber" id="rateCapSequenceNumber">
				</li>
		
				<li>
					<span>Customer Specific Rating Code: </span>
					<select name="customerSpecificRatingCode">
							<option value="-1" selected="selected">All</option>
							<c:forEach var="item" items="${ customerSpecificRatingCodes}">
						 		<option value="${item.key}">${item.value}</option>
					     	</c:forEach>
					</select>
				</li>
		
				<li>
					<span>Table Entry Effective Date (YYYY-mm-dd): </span> 
					<input type="text" name="tableEntryCreationDate" id="tableEntryCreationDate">
				</li>
		
				<li>
					<span>Table Entry Expiration Date (YYYY-mm-dd): </span> 
					<input type="text" name="tableEntryExpirationDate" id="tableEntryExpirationDate">
				</li>
		
				<li>
					<span>Rate Time Unit: </span>
					<select name="rateTimeUnit">
							<option value="-1" selected="selected">All</option>
							<c:forEach var="item" items="${ rateTimeUnit}">
						 		<option value="${item.key}">${item.value}</option>
					     	</c:forEach>
					</select>
				</li>
		
				<li>
					<span>Message Level Percent Discount: </span> 
					<input type="text" name="messageLevelPercentDiscount" id="messageLevelPercentDiscount">
				</li>
		
				<li>
					<span>Cap Threshold Code: </span> 
					<input type="text" name="capThresholdCode" id="capThresholdCode">
				</li>
		
				<li>
					<span>Minimum Time: </span> 
					<input type="text" name="minimumTime" id="minimumTime">
				</li>
		
				<li>
					<span>Increment Time: </span>
					<input type="text" name="incrementTime" id="incrementTime">
				</li>
		
				<li>
					<span>Increment Time Unit: </span>
					 <select name="incrementTimeUnit">
							<option value="-1" selected="selected">All</option>
							<option value=""></option>
							<option value="S">S</option>
							<option value="M">M</option>
					</select>
				</li>
			</ul>
			<input name="btnSearch" id="btnSearch" value="Search" type="button">
			<input name="btnExport" id="btnExport" value="Export excel" type="button" style="display: none;">
		    </form>
		    <div id="searchToolPriceTable" class="jtable-main-container"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function () {
	$('#btnSearch').click(function(){
		//search();
		$("#btnExport").show();
		var searchId ="#searchToolPriceTable";
		setupToolPlanTable(searchId);
		$(searchId).jtable('reload');
	});
	$('#btnExport').click(function(){
		if (confirm("Do you want to export to excel file?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': 'exportSearchToolPricePlan.htm?'+ $('#searchForm').serialize()}); 
			$('#lnkDwnldLnk')[0].click();    
			document.body.removeChild(link);
	    }
	});
});
function setupToolPlanTable(searchId)
{
	$(searchId).jtable({
		title: '',
	    paging : true,
		pagSize : 50,
		sorting: true,
        multiSorting: true,
        defaultSorting: 'ID ASC',
	    actions: {
	    	listAction: function (postData, jtParams) {
                return $.Deferred(function ($dfd) {
                	console.log("1");
                    $.ajax({
                        url: 'searchToolPricePlan.htm?jtStartIndex=' + jtParams.jtStartIndex + '&jtPageSize=' + jtParams.jtPageSize + '&jtSorting=' + jtParams.jtSorting,
                        type: 'POST',
                        dataType: 'json',
                        data: $('#searchForm').serialize(),
                        success: function (data) {console.log(data);
                            $dfd.resolve(data);
                        },
                        error: function () {
                            $dfd.reject();
                        }
                    });
                });
	    	}
	    },
	    fields: {
	    	checkOutUser:{
        		title: '<img src="images/checked_in.gif"/>',
        		display: function(data){
        			if (data.record){
        				//alert(data.record.checkOutUser);
        				if(data.record.status == 1){
	        				if (data.record.checkOutUser == currentUser){
	        					return '<img src="images/checked_out.gif" class="state" onclick="showEditToolPlanPage(\'' + data.record.ID +'\');" alt="Edit this tool plan"/>';
	        				}else{
	        					return '<img src="images/checked_out_other.gif" onclick="viewToolPlanPage(\'' + data.record.ID +'\');" class="state" alt="View this tool plan"/>';
	        				}
        				}else{
        					return '<img src="images/checked_in.gif" class="state" alt="Check out and edit" onclick="SEVENJ.checkOutToolPlan(\'' + data.record.ID + '\')" />';
        				}
        			}
        		},
        		sorting: false,
        		width: '1%'
        	},
            approveStatus: {
            	title: 'Status',
            	sorting: true,
            	width: '5%',
            	display: function(data){
            		if (data){
            			return SEVENJ.getToolPlanStatus(data.record.approveStatus);
            		}
            	}
            },
            ID: {
            	key: true,
            	title: 'PRG PLAN RATE REC',
            	sorting: true,
            	width: '5%'
            },
            weekDefinitionCode: {
                title: 'WEEK DFNTN CODE',
                sorting: true,
                width: '10%'
            },
            ratePeriodSequenceNumber:{
            	title: 'RATE PERD SEQ NBR',
                sorting: true,
                width: '10%'
            },
            typeOfCall:{
            	title: 'TYPE OF CALL',
                sorting: true,
                width: '10%'
            },
            speacialTypeOfCall:{
            	title: 'SPCL CALL TYPE CD',
                sorting: true,
                width: '10%'
            },
            originatingDestinationCode: {
                title: 'ORIG DEST CODE',
                width: '10%'
            },
            originatingSatelliteCode: {
                title: 'ORIG SATLT CODE',
                sorting: true,
                width: '10%'
            },
            terminatingDestinationCode: {
                title: 'TERM DEST CODE',
                sorting: true,
                width: '10%'
            },
            terminatingSatelliteCode: {
                title: 'TERM SATLT CODE',
                sorting: true,
                width: '10%'
            },
            rateCapSequenceNumber: {
                title: 'RATE CAP SEQ NBR',
                width: '10%',
                sorting: true
            },
            customerSpecificRatingCode:{
            	title: 'CUST SPFC RTG CODE',
                width: '10%',
                sorting: true
            },
            tableEntryEffectiveDate: {
                title: 'TAB ENT EFFV DT',
                display:function (data) {
                    return moment(data.record.tableEntryEffectiveDate).format('YYYY/MM/DD');
                },
                width: '10%',
                sorting: true
            },
            rateType: {
                title: 'RATE TYPE',
                width: '5%',
                sorting: true
            },
            rate: {
                title: 'RATE',
                width: '5%',
                sorting: true
            },
            rateTimeUnit: {
                title: 'RATE TIME UNIT',
                width: '10%',
                sorting: true
            },
            messageLevelPercentDiscount:{
            	title: 'MSG LEV PCT DSCNT',
                width: '10%',
                sorting: true
            },
            capThresholdCode: {
                title: 'CAP THRSHLD CODE',
                width: '5%',
                sorting: true
            },
            minimumTime:{
            	title: 'MINIMUM TIME',
                width: '10%',
                sorting: true
            },
            incrementTime:{
            	title: 'INCRMNT TIME',
                width: '10%',
                sorting: true
            },
            incrementTimeUnit:{
            	title: 'INCRMNT TIME UNIT',
                width: '10%',
                sorting: true
            },
            rateValidationIndicator:{
            	title: 'RATE VAL IND',
                width: '10%',
                sorting: true
            },
            tableEntryExpirationDate:{
            	title: 'TAB ENT EXP DT',
                width: '10%',
                sorting: true
            },
            tableEntryExpirationDate: {
                title: 'TAB ENT CRTN DT',
                display:function (data) {
                    return moment(data.record.tableEntryExpirationDate).format('YYYY/MM/DD');
                },
                width: '10%',
                sorting: true
            }
		}
    });  
}
function search(){
	SEVENJ.ajaxSubmit('#searchForm', 'getToolPlans.htm', searchSuccess);
}

function searchSuccess(){
	$("#toolPlanTable").jtable('reload');
}
</script>