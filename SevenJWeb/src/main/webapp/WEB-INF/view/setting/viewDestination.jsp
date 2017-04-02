<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Destination Management</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="contenttable">
		<form action="#" method="post">
			<div class="introText">
				<p>This page shows all destinations</p>
			</div>
			<div class="tabletitle">Destination</div>
			<div class="tablectrl">
				<table class="tablebuttonbar">
					<tbody>
						<tr>
							<td class="tablecontrols">
								<div>
									<div id="toolbar" class="Clearfix">
										<ul>
											<li><a href="#" id="createNewSetting">Create a New Destination</a></li>
										</ul>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="SettingTableContainer"></div>
			</div>
		</form>
	</div>
</div>
<div id="popupNewDestination"></div>
<div id="popupEditDestination"></div>

<script type="text/javascript">
$(document).ready(function () {
	$('#SettingTableContainer').jtable({
		title : "Destination List",		
		paging:true,
		pagSize:50,
		sorting:true,
		defaultSorting: 'destinationCode ASC',
		messages: {
			addNewRecord: 'Create a New Destination',
		    editRecord: 'Edit Destination',
		    deleteConfirmation: 'This setting will be deleted. Are you sure?',
		    save:'Ok',
		},
		actions : {
			listAction   : 'destinationListForJTable.htm',
			createAction : '',
			updateAction : 'updateDestination.htm',
			deleteAction : 'deleteDestination.htm'
		},
		fields : {
			destinationCode:{
				title : 'Destination Code',
				key : true,
			},
			destinationDescription:{
				title : 'Destinatio Description',
			},
			sortOrder:{
				title : 'Sort Order',
			}
		}
	});
	$('#SettingTableContainer').jtable('load');
	
	SEVENJ.setupDialog('#popupNewDestination', 'Create new Destination', '#createNewProjectForm', 'createNewDestination.htm',refreshTable);
	
	$('#createNewSetting').click(function(){
    	$.ajax({
    		type: "GET",
    		url : "createNewDestination.htm",
    		success : function(content) {
    			$('#popupNewDestination').html(content);
    			$('#popupNewDestination').dialog('open');
    		},
    		error : function(e) {
    			SEVENJ.showFatalError();
    		}
    	});
    });
	
	
	
	function refreshTable(){
		$('#SettingTableContainer').jtable('reload');
    }
});
</script>