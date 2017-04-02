<div class="bell-titlebar">
	<div class="bell-titlebar-left"></div>
	<div class="bell-titlebar-right"></div>
	<div class="bell-titlebar-center">
		<div class="bell-titlebar-title-panel">
			<h2>Price Plan Management - Project ${webSession.currentProject.projectName}</h2>
		</div>
		<div class="bell-titlebar-button-panel">&nbsp;</div>
	</div>
</div>
<div class="bell-window-content">
	<div class="bell-menu bell-menu-single clearfix">
		<div class="menu-wrapper">
			<ul>
				<li id="tabReject" title="My Tool Plans Rejected - Tab"><span class="tab">Rejected</span></li>
				<li id="tabPending" title="My Tool Plans Pending - Tab"><span class="tab">Pending</span></li>
				<li id="tabAprroved" title="My Tool Plans Aprroved - Tab"><span class="tab">Approved</span></li>
				<!-- <li class="" title="All My Tool Plans- Tab"><span class="tab">All</span></li>-->
			</ul>
		</div>
	</div>



	<div class="contenttable" id="">
		<form action="#" method="post">
			<div class="introText">
				<p>This page shows all tool plan of price plan</p>
			</div>
			<div class="tabletitle">Code: ${webSession.currentPricePlan.pricePlanCode} Name: ${webSession.currentPricePlan.pricePlanName}</div>
			<div class="tablectrl">
				<div id="toolbar" class="Clearfix">
					<div>
						<!-- Toolbar div Back -->
						<div class="testool" style="cursor: pointer;" title="back">
							<div>
								<div class="myClass" onClick="location='../viewProject.htm';">
									<div class="divCenter">
										<div class="aos" style="background-position: 0 -42px;"></div>
									</div>
								</div>
							</div>
						</div>
						<!-- end Toolbar div Back -->
						
						<!-- Price Plan menu -->
						<div id="toolbargroup" class="testool">
							<div>
								<div id="toolbar_group" onClick="document.getElementById('nav').style.display = 'block';"
									class="myClass" style="position: relative;">
									<div class="divCenter">
										<div class="aos" style="background-position: -21px -42px;"></div>
										<div class="aos1" style="background-position: -42px -84px;">&nbsp;</div>
										<div class="subnav_toolbar">
											<div>
												<ul class="top_toolbar">
													<li><a href="importPricePlan.htm">Import Exists Price Plans</a></li>
													<li><a href="#">Create Price Plan</a></li>
												</ul>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<!-- end Price Plan menu-->

						<!-- Price Plan Records menu -->
						<div id="toolbargroup" class="testool">
							<div>
								<div id="toolbar_group" onClick="document.getElementById('nav').style.display = 'block';" class="myClass" style="position: relative;">
									<div class="divCenter">
										<div class="aos" style="background-position: -84px -42px;"></div>
										<div class="aos1" style="background-position: -42px -84px;">&nbsp;</div>
										<div class="subnav_toolbar">
											<div>
												<ul class="top_toolbar">
													<li><a href="../CreateToolPlans.htm">Create Price
															Plan Records</a></li>
													<li><a href="#">Copy Price Plan Records</a></li>
													<li><a href="#">Update Price Plan Records</a></li>
													<li><a href="#">Comment Price Plan Records</a></li>
													<li><a href="#">Delete Price Plan Records</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end price Plan records menu-->
					</div>
				</div>
				<!-- end div toolbar -->
				<div id="toolPlanTable"></div>
			</div>
		</form>
	</div>
</div>