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
				<p>This page shows all User</p>
			</div>
			<div class="tabletitle">User</div>
			<div class="tablectrl">
				<table class="tablebuttonbar">
					<tbody>
						<tr>
							<td class="tablecontrols">
								<div>
									<div id="toolbar" class="Clearfix">
										<!-- <div>
											<div class="testool" title="Create new User">
												<div>
													<div id="createNewUser" class="myClass">
														<div class="divCenter">
															<div class="aos"
																style="background-position: -21px -42px;"></div>
														</div>
													</div>
												</div>
											</div>
										</div> -->
										<ul>
											<li>
												<a href="#" id="createNewUser">Create new User</a>
											</li>
										</ul>
									
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="userTableContainer"></div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#userTableContainer').jtable({
            title: '',
            paging : true,
			pagSize : 50,
			defaultSorting : 'UserName DESC',
			messages: {
				addNewRecord: 'Create New User',
			    editRecord: 'Edit User',
			   	save:'Ok'
			},
            actions: {
                listAction: 'userManagement.htm',
                createAction: 'createNewUser.htm',
                deleteAction: 'deleteUser.htm',
                updateAction: 'updateUser.htm'
            },
            fields:{
            	userID: {
                    key: true,
                    list: false,
                    create:false,
                    edit: false
                },
                username:{
                    title: 'User Name',
                    display: function(data){
                    	return '<a>' + data.record.username + '</a>';
                    },
                    width: '40%'
                },
                firstName: {
                    title: 'First Name',
                    width: '20%'
                },
                lastName: {
                    title: 'Last Name',
                    width: '30%'
                },
                password: {
                	title: 'Password',
                	list: false,
                    create:true,
                    edit: false,
                    type:'password'
                },
                roleId: {
                    title: 'Role',
                    width: '30%',
                    display: function (data) {
                    	return data.record.role.RoleName;
                    },
                    options: 'getRoles.htm'
                }
            },
            formCreated: function (event, data) {
            	//format layout 
            	data.form.css('width','260px');
            }
        });
        
        $('#userTableContainer').jtable('load');
        
        $('#createNewUser').click(function(){
        	 $('#userTableContainer').jtable('showCreateForm');
        });
    });
</script>
