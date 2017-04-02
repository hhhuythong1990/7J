<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Price Plan Management - Peats #${webSession.currentProject.projectPeat } Project '${webSession.currentProject.projectName }'</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="bell-menu bell-menu-single Clearfix">
		<div class="menu-wrapper">
			<ul>
				<li id="pricePlanTabId" title="My Tool Plans Rejected - Tab" style="cursor: pointer;" class="bell-menu-active"><a><span class="tab">Price Plan</span></a></li>
				<li id="tabPendingPricePlan" title="My Tool Plans Pending - Tab" style="cursor: pointer;"><a><span class="tab">Pending Price Plan</span></a></li>
			</ul>
		</div>
	</div>
	<div class="contenttable" id="tabContent"></div>
</div>

<div id="popupNewPricePlan"></div>
<div id="popupEditPricePlan"></div>

<script type="text/javascript">
    $(document).ready(function () {
        var newPricePlanPopupId = "#popupNewPricePlan";
        var editPricePlanPoupId = '#popupEditPricePlan';
        var createNewPricePlanFormId = "#createNewPricePlanForm";
        var editPricePlanFormId = "#editPricePlanForm";
        var pricePlanTableId = '#pricePlanTable';
        
        //Load default tab
        loadDefault();
        
       // setup new popup 
       SEVENJ.setupDialog(newPricePlanPopupId, 'Create new price plan', createNewPricePlanFormId, 'createNewPricePlan.htm',refreshTable);
       
       // setup edit popup
       SEVENJ.setupDialog(editPricePlanPoupId, 'Edit price plan', editPricePlanFormId, 'updatePricePlan.htm',refreshTable);
       
       // add event for price plan tab
       $('#pricePlanTabId').click(function(){
    	   SEVENJ.loadTabContentForPricePlan('#pricePlanTabId','#tabContent','getPricePlanTabContent.htm?status=1');
       });
       
       $('#tabPendingPricePlan').click(function(){
    	   SEVENJ.loadTabContentForPricePlan('#tabPendingPricePlan','#tabContent','getPricePlanTabContent.htm?status=0');
       });
       
       function loadDefault(){
    	   var forward = '${forward}';
    	   if (forward != ''){
    		   if (forward == 'viewPricePlan'){
    			    var projectId = '${webSession.currentProject.projectId}';
    				var tabId = SEVENJ.getCurrentTabId('${webSession.currentPricePlanStatusTab}');
    				var pricePlanCode = '${webSession.currentPricePlan.pricePlanCode}';
    				SEVENJ.loadTabContentForPricePlan(tabId,'#tabContent','viewPricePlanNew.htm?pricePlanCode=' + pricePlanCode);
    		   }else{
    			   //handle more cases here
    		   }
    	   }else{
    		   SEVENJ.loadTabContentForPricePlan('#pricePlanTabId','#tabContent','getPricePlanTabContent.htm?status=1');
    	   }
       }
 
       function refreshTable(){
      	   $(pricePlanTableId).jtable('reload');
       }  
    });
</script>