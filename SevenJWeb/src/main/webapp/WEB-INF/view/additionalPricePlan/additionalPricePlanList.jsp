<div id="portlet_priceplan" class="bell-window">
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
			<form action="#" method="post">
				<div class="introText">
					<p>This page shows all Additional Price Plan Infos</p>
				</div>
				<div class="tabletitle">Additional Price Plan Infos</div>
				<div class="tablectrl">
					<table class="tablebuttonbar">
						<tbody>
							<tr>
								<td class="tablecontrols">
									<div>
										<div id="toolbar" class="Clearfix">
											<ul>
												<li>
													<a href="searchADPricePlan.htm" onClick="location='searchADPricePlan.htm';">Search Additional Price Plan Info</a>
												</li>
												<li>
													<a href="CreateADPricePlan.htm" onClick="location='CreateADPricePlan.htm';">Create a new Additional Price Plan Info</a>
												</li>
												<li>
													<!-- <a href="generateAdditionalPricePlanList.htm" id="btnExtractExcel" onClick="location='generateAdditionalPricePlanList.htm';">Generate an Additional Price Plan Infos report</a> -->
													<a href="#" id="btnExtractExcel">Generate an Additional Price Plan Infos report</a>
												</li>
											</ul>
											<div>
											</div>
										</div>

									</div> 

								</td>
							</tr>
						</tbody>
					</table>
					<div id="appTable"></div>
				</div >
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8">
$(document).ready(function () {
	var additionalPPId = "#appTable";
	var editPricePlanPoupId = '#popupDesADPricePlan';
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
            listAction: 'getAdditionalPricePlanListForJTable.htm',
        },
        fields: {/* 
            PricePlanName: {
                title: 'Price Plan Name',
                display: function(data){
                	return '<a href="viewAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '&selectedTab=1">' + data.record.PricePlanName + '</a>';
                },
                width: '30%'
            },
            Description: {
                title: 'Description',
                display: function(data){
                	var $linkDes = $('<a href="#">Description</a>');
                   	$linkDes.click(function(){
                   		window.open("viewDescriptionAdditionalPricePlan.htm?pricePlanCode=" + data.record.PricePlanCode + "&selectedTab=1",'_blank','height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
                   	});
                   	return $linkDes;
                	//return '<a href="viewDescriptionAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '&selectedTab=1">' + "Description" + '</a>';
                }
            },
            PricePlanCode: {
                key: true,
                edit: false,
                title: 'Price Plan Code'
            },
            IsAccountBased: {
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
                 width: '30%'
            },
            IsToolPricingPlan: {
                title: 'Product/Tool',
                display: function (data) {
                	if (data.record.IsToolPricingPlan == '1'){
                		return "Tool";
                	}else{
                		return "Product";
                	}
                }
            },

            CustomAction: {
                   title: '',
                   display: function(data) {
                   	var $link = $('<img src="images/edit.png" title="Edit" class="myUpdate">');
                   	$link.click(function(){
                   		window.location.href ='editAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode;
       
                   	});
                   	return $link;
                   },
                   sorting: false,
                   create: false,
                   edit:false
               } */

        	checkOutUser:{
        		title: '',
        		display: function(data){
        			//return '<img src="images/checked_in.gif" class="state" alt="Edit" />';
        			return '<a href="editAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '"><img src="images/checked_in.gif" class="state" alt="Edit" /></a>';
        		},
        		sorting: false,
        		width: '1%'
        	},
            PricePlanName: {
                title: 'Price Plan Name',
                display: function(data){
                	return '<a href="viewAdditionalPricePlan.htm?pricePlanCode=' + data.record.PricePlanCode + '&selectedTab=1">' + data.record.PricePlanName + '</a>';
                },
                width: '20%'
            },
            Description: {
                title: 'Description',
                edit: false,
                display: function(data){
                	var $linkDes = $('<a href="#">Description</a>');
                   	$linkDes.click(function(){
                   		window.open("viewDescriptionAdditionalPricePlan.htm?pricePlanCode=" + data.record.PricePlanCode,'_blank','height=650,width=900,left=250,top=10,resizable=no,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=no,titlebar=no');
                   	});
                   	return $linkDes;
                },
                width: '8%'
            },
            PricePlanCode: {
                key: true,
                edit: false,
                title: 'Price Plan Code'
            },
            AccountLevel: {
                title: 'Account Level',
                width: '11%',
                display: function (data) {
                	return dat[data.record.AccountLevel-1];
                }
            },
            LineLevel: {
                title: 'Line Level',
                width: '8%',
                display: function (data) {
                	return dat[data.record.LineLevel-1];
                }
            },
            PrioprityCode: {
                title: 'Prioprity Code',
                width: '10%',
                display: function (data) {
                	return dat[data.record.PrioprityCode-1];
                }
            },
            Usoc: {
            	 title: 'USOC',
                 width: '20%'
            },
            IsToolPricingPlan: {
                title: 'Tool',
                display: function (data) {
                	return dat[data.record.IsToolPricingPlan-1];
                }
            },
 			}
    });
    
    // load data for jtable
    $(additionalPPId).jtable('load');
    $('#btnExtractExcel').click(function() {
		if (confirm("Do you want to generate this report to excel format?") == true) {
			var link = document.createElement("a");    
			link.id="lnkDwnldLnk";
			document.body.appendChild(link);
			$("#lnkDwnldLnk").attr({'href': 'exportToExcelGenerateAdditionalPricePlanList.htm'}); 
			$('#lnkDwnldLnk')[0].click();    
			document.body.removeChild(link);
	    }
	});
});

</script>

