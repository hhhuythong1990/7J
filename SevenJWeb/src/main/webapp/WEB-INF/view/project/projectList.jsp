<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Project Management</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="contenttable">
		<form action="#" method="post">
			<div class="introText">
				<p>This page shows all projects</p>
			</div>
			<div class="tabletitle">Projects</div>
			<div class="tablectrl">
				<table class="tablebuttonbar">
					<tbody>
						<tr>
							<td class="tablecontrols">
								<div>
									<div id="toolbar" class="Clearfix">
										<ul>
											<li><a href="#" id="createNewProject">Create a New Project</a></li>
										</ul>
										<!-- <div>
											<div class="testool" title="Create new project">
												<div>
													<div id="createNewProject" class="myClass">
														<div class="divCenter">
															<div class="aos"
																style="background-position: -21px -42px;"></div>
														</div>
													</div>
												</div>
											</div>
										</div> -->
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="ProjectTableContainer"></div>
			</div>
		</form>
	</div>
</div>
<div id="popupNewProject"></div>
<div id="popupEditProject"></div>
<script type="text/javascript">
	var projectTableId = '#ProjectTableContainer';
	var newProjectPopupId = "#popupNewProject";
	var editProjectPopupId = '#popupEditProject';
	var createNewProjectFormId = "#createNewProjectForm";
    var editProjectFormId = "#editProjectForm";
	
    $(document).ready(function () {
        $('#ProjectTableContainer').jtable({
            title: '',
            paging : true,
			pagSize : 50,
			sorting : true,
			defaultSorting : 'createdDate DESC',
			messages: {
				addNewRecord: 'Create a New Project',
			    editRecord: 'Edit Project',
			    deleteConfirmation: 'This project will be deleted. Are you sure?',
			    save:'Ok',
			},
            actions: {
                listAction: 'projectListForJTable.htm',
                //createAction: 'createNewProject.htm',
                deleteAction: 'deleteProject.htm',
                //updateAction: 'updateProject.htm'
            },
            fields: {
            	projectId: {
                    key: true,
                    list: false,
                    create:false,
                    edit: false
                },
                projectName: {
                    title: 'Project Name',
                    display: function(data){
                    	return '<a href="viewProject.htm?projectId=' + data.record.projectId + '">' + data.record.projectName + '</a>';
                    },
                    
                    width: '40%',
                    inputClass:'validate[required],maxSize[225]'
                },
                peat: {
                    title: '#Peats',
                    width: '20%',
                    inputClass:'validate[required],maxSize[15]'
                },
                createdDate: {
                    title: 'Created On',
                    width: '30%',
                    display: function (data) {
                        //return moment(data.record.createdDate).format('MMM DD, YYYY HH:mm:ss');
                        return moment(data.record.createdDate).format('MMM DD, YYYY');
                    },
                    create: false,
                    edit: false
                },
                createdUser: {
                    title: 'Created By',
                    width: '30%',
                    create: false,
                    edit: false
                },
                CustomAction: {
                    title: '',
                    display: function(data) {
                    	var $link = $('<img src="images/edit.png" title="Edit" class="myUpdate">');
                    	$link.click(function(){
                    		console.log(data.record.projectId);
                    		showEditForm(data.record.projectId);
                    	});
                    	return $link;
                    },
                    sorting: false,
                    create: false,
                    edit:false
                }
            },
            formCreated: function (event, data) {
            	//format layout 
            	data.form.css('width','400px');
            	data.form.css('margin-top','10px');
            	data.form.find('input[name=projectName]').css('width','300px');
            	data.form.find('input[name=peat]').css('width','120px');
            	//register validation
                data.form.validationEngine({'binded':false});
            },
            formSubmitting: function (event, data) {
            	data.form.validationEngine('hide');
                data.form.validationEngine('detach');
                data.form.validationEngine({'binded':false});
                return data.form.validationEngine('validate');
            },
            recordUpdated:function(event, data){
                $('#ProjectTableContainer').jtable('reload');
            },
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
        });
        
        $('#ProjectTableContainer').jtable('load');
        
        $('#createNewProject').click(function(){
        	$.ajax({
        		type: "GET",
        		url : "createNewProject.htm",
        		success : function(content) {
        			$(newProjectPopupId).html(content);
        			$(newProjectPopupId).dialog('open');
        		},
        		error : function(e) {
        			SEVENJ.showFatalError();
        		}
        	});
        });
        function showEditForm(projectCode){
	     	   SEVENJ.loadPoupContent('#popupEditProject', 'updateProject.htm?projectCode=' + projectCode);
	     }
        SEVENJ.setupDialog(newProjectPopupId, 'Create new project', createNewProjectFormId, 'createNewProject.htm',refreshTable);
        SEVENJ.setupDialog(editProjectPopupId, 'Edit project', editProjectFormId, 'updateProject.htm',refreshTable);
        function refreshTable(){
       	   $(projectTableId).jtable('reload');
        }
    });
</script>
