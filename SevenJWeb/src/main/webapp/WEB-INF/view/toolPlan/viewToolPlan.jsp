<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-1_0-rt.tld" %>
<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>View Price Plan Record</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
<div class="contenttable">
	<div class="introText">
		<p>View Price Plan Record</p>
	</div>
	<div class="upperButtonBar">
		<div class="buttonBar">
		      <input class="formButton" type="button" onclick="SEVENJ.back();" value="Back to price plan"/>
		</div>
	</div>
	<div class="buttonBar section"><a id="commentSection">Comments</a></div>
	<div id="commentContent">
	    <div id="commentTable"></div>
	</div>
	<div class="buttonBar section">
		<a id="historySection">Histories</a>
	</div>
	<div id="historyContent">
		<div id="historyTable"></div>
	</div>
	<div class="buttonBar section">
		<a id="historySection">Detail Information</a>
	</div>
	<div id="viewToolPlanTable"></div>
</div>
</div>
<c:set var="currentToolPlan" value="${ toolPlan }"></c:set>
<script type="text/javascript">
$(document).ready(function () {
	toogleSection('#commentSection','#commentContent');
	toogleSection('#historySection','#historyContent');
	
	// default hide comment and history
	$('#commentContent').hide();
	$('#historyContent').hide();
	
	$('#commentTable').jtable({
		title: '',
        paging : false,
		sorting : false,
		pagSize : 50,
        actions: {
            listAction: "getCommentsByToolPlan.htm?toolPlanId=${toolPlan.ID}" 
        },
        fields: {
        	comment:{
        		title: "Comment",
        		width: '40%'
        	},
        	commentUser:{
        		title: "Comment Date",
        		width: '30%'
        	},
        	commentDate: {
        		title: 'Comment Date',
        		width: '30%',
        		display:function (data) {
                    return moment(data.record.commentDate).format('YYYY/MM/DD hh:mm:ss');
                }
        	}
        }
	});
	
	$('#commentTable').jtable('load');
	
	//Setup history
	
	$('#historyTable').jtable({
		title: '',
        paging : false,
		sorting : false,
		pagSize : 50,
        actions: {
            listAction: "getHistoriesByToolPlan.htm?toolPlanId=${toolPlan.ID}" 
        },
        fields: {
        	ChangeUser:{
        		title: "Change User",
        		width: '30%'
        	},        	
        	HistoryDate: {
        		title: 'History Date',
        		width: '30%',
        		display:function (data) {
                    return moment(data.record.HistoryDate).format('YYYY/MM/DD hh:mm:ss');
                }
        	},
        	ViewHistoryDetailAction: {
                    title: '',
                    display: function(data) {
                    	var $link = $('<a style="cursor:pointer;">View Detail</a>');
                    	$link.click(function(){
                    		viewDetailHistory(data.record.ID);
                    	});
                    	return $link;
                    }
            },
        	CompareHistoryAction: {
                title: '',
                display: function(data) {
                	var $link = $('<a style="cursor:pointer;">Compare with current Tool Plan</a>');
                	$link.click(function(){
                		compareHistory(data.record.ID);               		
                	});
                	return $link;
             }
         }
        }
	});
	$('#historyTable').jtable('load');
	
	$('#viewToolPlanTable').jtable({
		title: '',
        paging : false,
		sorting : false,
		pagSize : 50,
        actions: {
            listAction: 'getToolPlanById.htm?toolPlanId=${toolPlan.ID}'
        },
        fields: {
        	PricePlanCode: {
            	title: 'PRG PLAN CODE',
            	display: function(data){
            		return data.record.PricePlan.pricePlanCode;
            	},
            	width: '5%'
            },
            weekDefinitionCode: {
                title: 'Week Def Code',
                width: '10%'
            },
            ratePeriodSequenceNumber: {
            	title: 'RATE PERD SEQ NBR',
            	width: '10%'
            },
            typeOfCall: {
            	title: 'TYPE OF CALL',
            	width: '10%'
            },
            speacialTypeOfCall: {
            	title: 'SPCL CALL TYPE CD',
            	width: '10%'
            },            
            originatingDestinationCode: {
                title: 'ORIG DEST CODE',
                width: '10%'
            },
            originatingSatelliteCode: {
                title: 'ORIG SATLT CODE',
                width: '10%'
            },
            terminatingDestinationCode: {
                title: 'TERM DEST CODE',
                width: '10%'
            },
            terminatingSatelliteCode: {
                title: 'TERM SATLT CODE',
                width: '10%'
            },
            rateCapSequenceNumber: {
                title: 'RATE CAP SEQ NBR',
                width: '10%'
            },
            customerSpecificRatingCode: {
            	title: 'CUST SPFC RTG CODE',
            	width: '10%'
            },
            tableEntryEffectiveDate: {
                title: 'TAB ENT EFFV DT',
                display:function (data) {
                    return moment(data.record.tableEntryEffectiveDate).format('YYYY/MM/DD');
                },
                width: '10%'
            },
            rateType: {
                title: 'RATE TYPE',
                width: '5%'
            },
            rate: {
                title: 'RATE',
                width: '5%'
            },
            rateTimeUnit: {
                title: 'RATE TIME UNIT',
                width: '10%'
            },
            messageLevelPercentDiscount:{
            	title: 'MSG LEV PCT DSCNT',
            	width: '10%'
            },
            capThresholdCode: {
                title: 'CAP THRSHLD CODE',
                width: '10%'
            },
            minimumTime: {
            	title: 'MINIMUM TIME',
            	width: '5%'
            },
            incrementTime:{
            	title: 'INCRMNT TIME',
            	width: '5%'
            },
            incrementTimeUnit: {
            	title: 'INCRMNT TIME UNIT',
            	width: '5%'
            },
            rateValidationIndicator: {
            	title: 'RATE VAL IND',
            	width: '5%'
            },
            tableEntryExpirationDate: {
                title: 'TAB ENT EXP DT',
                display:function (data) {
                    return moment(data.record.tableEntryExpirationDate).format('YYYY/MM/DD');
                },
                width: '10%'
            },
            tableEntryCreationDate: {
                title: 'TAB ENT CRTN DT',
                display:function (data) {
                    return moment(data.record.tableEntryCreationDate).format('YYYY/MM/DD');
                },
                width: '10%'
            }
        }
	});
	
	$('#viewToolPlanTable').jtable('load');
});

function toogleSection(sectionId, contentId){
	$(sectionId).click(function(){
		 $(contentId).toggle();
	});
}

function viewDetailHistory(id){
	SEVENJ.redirectNewTab("viewToolPlanHistory.htm?historyId=" + id);
}

function compareHistory(id){
	SEVENJ.redirectNewTab("compareToolPlanHistory.htm?historyId=" + id + '&toolPlanId=${toolPlan.ID}');
}
</script>