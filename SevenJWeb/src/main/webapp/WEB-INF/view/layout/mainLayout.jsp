<%@page import="com.sevenj.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tile" uri="/WEB-INF/tld/tiles-jsp.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tile:insertAttribute name="title"/></title>
<link rel="stylesheet" href="css/MyStyle.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/general.css">
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css" href="css/window.css">
<link rel="stylesheet" type="text/css" href="css/portal.css">
<link rel="stylesheet" type="text/css" href="css/forms.css">
<link rel="stylesheet" type="text/css" href="css/quicklinks.css">
<link rel="stylesheet" type="text/css" href="css/userinfo.css">
<link rel="stylesheet" type="text/css" href="css/content.css">
<link rel="stylesheet" type="text/css" href="css/systemtask.css">
<link rel="stylesheet" type="text/css" href="css/toolbar.css">
<link rel="stylesheet" type="text/css" href="css/sevenj.css">
<link rel="stylesheet" type="text/css" href="css/cssError.css">

<script src="scripts/table.js" type="text/javascript"></script>
<script type="text/javascript" src="scripts/jquery/jquery-2.1.1.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-ui-1.10.3.custom.js"></script>
<script src="scripts/buttons.js" type="text/javascript"></script>
<script src="scripts/util.js" type="text/javascript"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

<!-- jTable plugin -->
<link href="scripts/jtable/themes/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="theme/metroblue/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/jtable/jquery.jtable.js"></script> 
<script type="text/javascript" src="scripts/moment.js"></script>  

<!-- Validate Engine plugin -->
<!-- <script src="scripts/validationEngine/jquery.validate.js" type="text/javascript" charset="utf-8"></script>
<script src="scripts/validationEngine/additional-methods.js" type="text/javascript" charset="utf-8"></script>
 -->

<script src="scripts/validationEngine/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="scripts/validationEngine/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css"/>

<!-- J7 javascript library -->
<script src="scripts/sevenj.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function(){
 var clickable=false
	//$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled - Adds empty span tag after ul.subnav

	$("#menu_group  ").click(function() { //When trigger is clicked...
		clickable=!clickable;
	
		//Following events are applied to the subnav itself (moving subnav up and down)
		$(this).parent().find("div.subnav").slideDown('fast').show(); //Drop down the subnav on click

		$(this).parent().hover(function() {
		}, function(){
			$(this).parent().find("div.subnav").slideUp('fast'); //When the mouse hovers out of the subnav, move it back up
		});
		
		if(clickable==false){
		$(this).parent().find("div.subnav").slideUp('fast');
		}

		//Following events are applied to the trigger (Hover events for the trigger)
		}).hover(function() {
			$(this).addClass("subhover"); //On hover over, add class "subhover"
		}, function(){	//On Hover Out
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"
	});
   
	
	//register error dialog
    $("#errorDialog").dialog({
     autoOpen: false,
     show: 'fade',
     hide: 'fade',
     modal: true,
     title: 'Warning messasge',
     buttons: [{
         text: 'Close',
         click: function () {
        	 $("#errorDialog").dialog('close');
         }
     }]
 });
	
 // setup dialog
 //SEVENJ.setupDialog('popupDialog', 'Copy tool plans', 'copyToolPlanForm', 'copyToolPlans.htm');
  $(document).ajaxSend(function(event, request, settings) {
  	  $('#loading-indicator').show();
  	});

  	$(document).ajaxComplete(function(event, request, settings) {
  	  $('#loading-indicator').hide();
  	});

});
</script>
    
</head>
<body>
    <div class="bell-header">
        <div id="bell-header-logo">
            <img src="images/belllogo.gif" id="bell-title" alt="Bell 7J Portal Home" /></div>
	  	<div id="bell-header-menu">
	
			<div class="clearfix">
		             <div class="welcome">Welcome,  <a href="AdminPage.htm"> ${webSession.currentUser.fullname }</a></div>
		            <div class="left">
		            </div>
		            <div class="home">
		                <a href="projectList.html"><img src="images/icoHome.png" style="vertical-align: middle;"></a>
		                &nbsp; &nbsp;<a href="projectList.htm">Home </a>
		            </div>
		            <div class="line">
		            </div>
		            
		            <div class="line"></div>	    
			       <div class="center">
		                <a href="getAdditionalPricePlanList.htm">Additional Price Plan Info</a></div>
		            <div class="line"></div>
		            <div class="center">
		                <a href="settingList.htm">Destinations</a></div>
		            <div class="line"></div>
		            <div class="center">
		                <a href="logout.htm">Log out</a></div>
		            <div class="line"></div>
		          
		            <div id="menu_group" style="cursor: hand;" class="main_menulast" >
		                <p class="aos" style="background-position:-42px -84px;margin-top:0;"></p>
					  	<div class="subnav">
		                      <div>
		                      		<div>
			                      		<ul class="top_menu">
			                               	<li>
			                                   	 <a href="userManagement.htm" id="st">User Management</a>
			                                   </li>
			                                <li>						
												<a href="#" target="_blank">Help</a>						
											</li>
			                                  
			                             </ul>
		                           </div>
		                      </div>  
						</div>
		        </div>
			
				<div class="line">
		         </div>
		</div>
	
	<div style="float:right;" class="test">
		<input type="text" value="">
		<input type="button" class="" value="Go">	
		<a href="SearchGlobal.htm" >Advance Search</a>
	</div>
       
    </div>
	</div>
    <div id="Home" class="bell-book">
        <div class="bell-book-content">
            <div id="page" class="bell-page">
                <div class="bell-2col-layout">
                    <div id="bell-content-col">
                        <div id="bell-content-col-inner">
                            <div>
                            </div>
                            <div id="SiteMapPathBook" class="none">
                                <div class="bell-book-content">
                                    <div id="SiteMapPathPage" class="bell-page">
                                        <div>
                                        </div>
                                        <div id="portlet_sitemappath" class="bell-window">
                                            <div class="bell-window-content">
                                                <tile:insertAttribute name="menuNavigator"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="ContentBook" class="none">
                                <div class="bell-book-content">
                                    <div id="ContentPage" class="bell-page">
                                        <div>
                                        </div>
                                        <a name="wlp_portlet_priceplan"></a>
                                        <div id="portlet_priceplan" class="bell-window">
                                          <tile:insertAttribute name="pageContent"/>
                                        </div>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                
                </div>
            </div>
        </div>
    </div>
    <div class="bell-footer">
    </div>
    <div id="errorDialog"></div>
    <div id="popupDialog"></div>
    <img src="images/ajax-loader.gif" id="loading-indicator" style="display:none" />
    <input id="role" style="display: none" value="${webSession.getCurrentUser().getRole().getRoleId()}">
    
</body>
</html>