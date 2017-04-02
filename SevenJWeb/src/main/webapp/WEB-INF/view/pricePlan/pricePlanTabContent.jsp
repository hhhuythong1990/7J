<%@ taglib prefix="c" uri="/WEB-INF/tld/c-1_0-rt.tld" %>
<form action="#" method="post">
	<div class="introText">
	    <c:if test="${webSession.currentPricePlanStatusTab == 1}">
	    	<p>This page shows all price plan for project '${webSession.currentProject.projectName }'</p>
	    </c:if>
		<c:if test="${webSession.currentPricePlanStatusTab == 0}">
	    	<p>This page shows price plans which has pending price plan rate record for project '${webSession.currentProject.projectName }'</p>
	    </c:if>
	</div>
	<div class="tabletitle">Price Plans</div>
	<div class="tablectrl">
		<table class="tablebuttonbar">
			<tbody>
				<tr>
					<td class="tablecontrols">
						<div>
							<div id="toolbar" class="Clearfix">
								<div>
									<ul>
										<li>
											<a href="#" id="backToProjectManagement">Back To Project Management</a>
										</li>
										<li>
											<a href="createNewPricePlan.htm" id="createNewPricePlan">Create a New Price Plan</a>
										</li>
									</ul>
								</div>
							</div>
						</div> 
					</td>
				</tr>
			</tbody>
		</table>
		<div id="pricePlanTable"></div>
	</div>
</form>
<script type="text/javascript">
    $(document).ready(function () {
    	var pricePlanTableId = '#pricePlanTable';
    	 var newPricePlanPopupId = "#popupNewPricePlan";
         var editPricePlanPoupId = '#popupEditPricePlan';
        
        $('#backToProjectManagement').click(function(){
        	SEVENJ.redirect("projectList.htm");
        });

        
	   	function showEditForm(pricePlanCode){
	    	   //SEVENJ.loadPoupContent('#popupEditPricePlan', 'updatePricePlan.htm?pricePlanCode=' + pricePlanCode);
	   			SEVENJ.redirect("editPricePlan.htm?pricePlanCode="+pricePlanCode);
	    }
	   	 
        // setup jtable
    	function setupPricePlanTable(pricePlanTableId, listActionUrl)
        {
        	
    		$(pricePlanTableId).jtable({
   	            title: '',
   	            paging : true,
   				pagSize : 50,
   				sorting : true,
   				defaultSorting : 'creationDate DESC',
   				messages: {
   				    editRecord: 'Edit Price Plan',
   				    deleteConfirmation: 'This price plan will be deleted. Are you sure?'
   				},
   	            actions: {
   	                listAction:listActionUrl,
   	                deleteAction: 'deletePricePlan.htm'
   	            },
   	            fields: {
   	                pricePlanName: {
   	                    title: 'Price Plan Name',
   	                    display: function(data){
   	                    	return '<a style="cursor:pointer" onclick="viewPricePlan(\''+data.record.pricePlanCode+ '\')\">' + data.record.pricePlanName + '</a>';
   	                    },
   	                    width: '40%'
   	                },
   	                pricePlanCode: {
   	                    key: true,
   	                    title: 'Price Plan Code'
   	                },
   	                creationDate: {
   	                    title: 'Creation On',
   	                    width: '30%',
   	                    display: function (data) {
   	                        return moment(data.record.creationDate).format('MM/DD/YYYY HH:mm:ss');
   	                    },
   	                    edit: false
   	                },
   	                creationUser: {
   	                    title: 'Created By',
   	                    width: '20%',
   	                    inputClass:'validate[required],maxSize[15]',
   	                    edit: false
   	                },
   	                CustomAction: {
   	                    title: '',
   	                    display: function(data) {
   	                    	var $link = $('<img src="images/edit.png" title="Edit" class="myUpdate">');
   	                    	$link.click(function(){
   	                    		showEditForm(data.record.pricePlanCode);
   	                    	});
   	                    	return $link;
   	                    },
   	                    sorting: false,
   	                    create: false,
   	                    edit:false
   	                }
   	            }
   	        }); 
        }
   		 
   //set up price plan table
    	setupPricePlanTable(pricePlanTableId,'getPricePlanListForJTable.htm');
    	$(pricePlanTableId).jtable('reload');
    });
    
    function viewPricePlan(pricePlanCode){
    	SEVENJ.loadTabContentForPricePlan(SEVENJ.getCurrentTabId('${webSession.currentPricePlanStatusTab}'),'#tabContent','viewPricePlanNew.htm?pricePlanCode=' + pricePlanCode);
    }
</script>