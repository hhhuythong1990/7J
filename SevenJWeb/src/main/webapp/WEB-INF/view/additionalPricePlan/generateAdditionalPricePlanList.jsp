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
<!--  <div class="bell-window-content">
	<div class="contenttable">
		<div class="introText">
			<p>Extract a Excel for Generating an Additional Price Plan Info Report</p>
		</div>
		<form id="toolPlanForm" name="toolPlanForm">
			<div class="upperButtonBar">
				<div class="buttonBar">
					<input class="formButton" id="btnExtractExcel" value="Export Excel" type="button"> <input
						class="formButton" value="Back" type="button" onclick="goBack()">
				</div>
			</div>
			<div class="stepIntro"></div>
			
			<div class="upperButtonBar">
				<div class="buttonBar">
					<div id="viewExtractToolPlanTable"></div>
				</div>
			</div>
		</form>
	</div>
</div>-->
<div id="generateTable" class="generateTable" style="overflow: scroll;"></div>
<script>

$(document).ready(function () {
	var additionalPPId = "#generateTable";
	var  dat=["Eligible","Not eligible","Not Applicable","Account","Line","AA2","AD1",
	          "AD5","AD9","AE4","AE8","AF5","AG1","AK1","AL1","AM1","AV5","AV9","AX0","AX1",
	          "AX3","AX4","AX5","AX6","AX7","AX8","AY1","AY2","AY4","AY5","AY7","AY9","AZ2","AZ6",
	          "AZ8","AZ9","BA1","BA3","BA6","BG2","BG3","BG6","BG8","BG9","BH0","BH1","BH2","BH3","BH6",
	          "FA3","Tool","Product","6 Rows on Flex with Rate Type P for all countries to receive the MSA Base Rates minus 40%","Top 28 & 29+ on Flex with Base Rates",
	          "4 Rows on Flex with Rate Type C for all countries to receive the MSA Base rates","Top 28 on Flex with Base Rates","Canada and US",
	          "DDD, Can & US 25? - all other DDD and Overseas are MSA Base rates","Top 28 & 29+ Overseas only","Can, US & Top 28 & 29+ Overseas","120 mins",
	       	"250 mins","300 mins","1200 mins","1500 mins","45000 mins","$20 that is tied to the MRC of $12.95","$25.00 ","250 min blocks",
	       	"100 minutes Free Airtime","250 minutes Free Airtime","Overseas messages only","Domestic and US messages only","Overseas messages only","Domestic and US messages only",
	       	"Overseas messages only","Domestic and US messages only","Overseas messages only","Domestic and US messages only","Overseas messages only","Domestic and US messages only"];
	 // setup jtable
    $(additionalPPId).jtable({
        title: '',
        paging : true,
		pagSize : 50,
		sorting : true,
		defaultSorting : 'creationDate DESC',
        actions: {
            listAction: 'generateAdditionalPricePlanList.htm',
        },
        fields: {
            PricePlanName: {
                title: 'Price Plan Name',
                display: function(data){
                	return '<a href="viewAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '&selectedTab=1">' + data.record.PricePlanName + '</a>';
                },
                width: '30%'
            },
            PricePlanCode: {
                key: true,
                edit: false,
                title: 'Price Plan Code'
            },
            AccountLevel: {
                title: 'Account/Line Based',
                width: '10%',
                display: function (data) {
                	if (data.record.IsAccountBased == '1'){
                		return "Account";
                	}else{
                		return "Line";
                	}
                }
            },
            PrioprityCode: {
                title: 'Prioprity Code',
                width: '10%'
            },
            Usoc: {
            	 title: 'USOC',
                 width: '10%'
            },
            IsToolPricingPlan: {
                title: 'Product/Tool',
             	 width: '10%',
                display: function (data) {
                	if (data.record.IsToolPricingPlan == '1'){
                		return "Tool";
                	}else{
                		return "Product";
                	}
                }
            },
            NoOverseaDest: {
                title: 'No Overseas Destinations',
                width:'30%',
                display: function (data) {
                	if (data.record.NoOverseaDest == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.NoOverseaDest == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.NoOverseaDest-1];
                	}
                }
            },
            Top28OverseaDest: {
                title: 'Top 28 Overseas Destinations Only',
                width:'3%',
                display: function (data) {
                	if (data.record.Top28OverseaDest == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Top28OverseaDest== '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Top28OverseaDest-1];
                	}
                }
            },
            T29OverseaDest: {
                title: 'Top 28 & 29 Overseas Destinations',
                width:'3%',
                display: function (data) {
                	if (data.record.T29OverseaDest == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.T29OverseaDest== '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.T29OverseaDest-1];
                	}
                }
            },
            BlockOfTime: {
                title: 'Block of time',
                width:'3%',
                display: function (data) {
                	if (data.record.BlockOfTime == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.BlockOfTime== '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.BlockOfTime-1];
                	}
                }
            },
            DollarCap: {
                title: 'Dollar CAP',
                width:'3%',
                display: function (data) {
                	if (data.record.DollarCap == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.DollarCap== '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.DollarCap-1];
                	}
                }
            },
            BillMinium: {
                title: 'Bill Minium',
                width:'3%',
                display: function (data) {
                	if (data.record.BillMinium== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.BillMinium== '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.BillMinium-1];
                	}
                }
            },
            Quantity: {
                title: 'Quantity',
                width:'3%',
                display: function (data) {
                	if (data.record.Quantity == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Quantity == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Quantity-1];
                	}
                }
            },
            ShortHaulRule: {
                title: 'Short Haul Rule',
                width:'3%',
                display: function (data) {
                	if (data.record.ShortHaulRule == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.ShortHaulRule == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.ShortHaulRule-1];
                	}
                }
            },
            CostCompareOnRule: {
                title: 'Cost Comparison Rule',
                width:'3%',
                display: function (data) {
                	if (data.record.CostCompareOnRule == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.CostCompareOnRule == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.CostCompareOnRule-1];
                	}
                }
            },
            WithSurcharges: {
                title: 'With SurCharges',
                width:'3%',
                display: function (data) {
                	if (data.record.WithSurcharges == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.WithSurcharges == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.WithSurcharges-1];
                	}
                }
            },
            MultiRateSchedule: {
                title: 'Mutiple Rate Schedules',
                width:'3%',
                display: function (data) {
                	if (data.record.MultiRateSchedule == '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.MultiRateSchedule == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.MultiRateSchedule-1];
                	}
                }
            },
            AirTime: {
                title: 'AirTime',
                width:'3%',
                display: function (data) {
                	if (data.record.AirTime== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.AirTime == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.AirTime-1];
                	}
                }
            },
            VolumeDiscount: {
                title: 'Volume Discount',
                width:'3%',
                display: function (data) {
                	if (data.record.VolumeDiscount== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.VolumeDiscount == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.VolumeDiscount-1];
                	}
                }
            },
            ContractDiscount: {
                title: 'Contract Discount',
                width:'3%',
                display: function (data) {
                	if (data.record.ContractDiscount== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.ContractDiscount == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.ContractDiscount-1];
                	}
                }
            },
            Increments60: {
                title: '60/60 Increment Only',
                width:'3%',
                display: function (data) {
                	if (data.record.Increments60== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Increments60 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Increments60-1];
                	}
                }
            },
            Increments60or30: {
                title: '60/60 or 30/6 Increments',
                width:'3%',
                display: function (data) {
                	if (data.record.Increments60or30== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Increments60or30 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Increments60or30-1];
                	}
                }
            },
            Increments30: {
                title: ' 30/6 Increments',
                width:'3%',
                display: function (data) {
                	if (data.record.Increments30== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Increments30 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Increments30-1];
                	}
                }
            },
            Increments10: {
                title: '10/10 Increments',
                width:'3%',
                display: function (data) {
                	if (data.record.Increments10== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Increments10 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Increments10-1];
                	}
                }
            },
            Increments1: {
                title: '1/1 Increments',
                width:'3%',
                display: function (data) {
                	if (data.record.Increments1== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Increments1 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Increments1-1];
                	}
                }
            },
            PeakOffPeak: {
                title: 'Peak Off-Peak',
                width:'3%',
                display: function (data) {
                	if (data.record.PeakOffPeak== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.PeakOffPeak == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.PeakOffPeak-1];
                	}
                }
            },
            Is247: {
                title: '24/7',
                width:'3%',
                display: function (data) {
                	if (data.record.Is247== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Is247 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Is247-1];
                	}
                }
            },
            Thd1: {
                title: 'TDH1',
                width:'3%',
                display: function (data) {
                	if (data.record.Thd1== '1'){
                		return  $('<img src="images/check.png">');
                	}else if(data.record.Thd1 == '2'){
                		return "No";
                	}
                	else{
                		return dat[data.record.Thd1-1];
                	}
                }
            },
            
               }
    });
    
    // load data for jtable
    $(additionalPPId).jtable('load');
    
});

function goBack(){
	window.history.back();
}
</script>