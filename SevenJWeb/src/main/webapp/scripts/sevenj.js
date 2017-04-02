var ERROR_DIALOG_ID = "#errorDialog";
var RESULT_OK = "OK";
var RESULT_ERROR = "ERROR";
// handle tabs on view price plan page
var tabRejectedId = '#tabRejected';
var tabPendingId = '#tabPending';
var tabApprovedId = '#tabApproved';
var tabAll = '#tabAll';
var tabPricePlanId='#pricePlanTabId';// get id  Id tab price plan
var tabPendingPricePlanId='#tabPendingPricePlan';
var toolPlanTabs = [{id: tabRejectedId, title: 'Rejected'}, {id:tabPendingId, title:'Pending'}, {id: tabApprovedId, title:'Approved'}, {id: tabAll, title:'All'}];
var pricePlanTabs=[{id:tabPricePlanId, title :'PricePlan'},{id:tabPendingPricePlanId, title:'Pending Price Plans'}];//
var activeMenuClass = 'bell-menu-active';
var SEVENJ={
   showError: function(message){
	   $(ERROR_DIALOG_ID).html(message);
	   $(ERROR_DIALOG_ID).dialog('option', 'title', 'System error');
	   $(ERROR_DIALOG_ID).dialog('open');
   },
   showWarning: function(message){
	   $(ERROR_DIALOG_ID).html(message);
	   $(ERROR_DIALOG_ID).dialog('option', 'title', 'Warning message');
	   $(ERROR_DIALOG_ID).dialog('open');
   },
   showFatalError: function(){
	   SEVENJ.showError("An error occured while communicating to the server.");
   },
   setupDialog: function(dialogId, dialogTitle, formId, actionUrl, callBackHandler){
   	$(dialogId).dialog({
           autoOpen: false,
           show: 'fade',
           hide: 'fade',
           width: 'auto',
           modal: true,
           title: dialogTitle,
           buttons:
                   [{ //Cancel button
                   	id: 'cancel_'+dialogId,
                       text: 'Cancel',
                       click: function () {
                       	$(dialogId).dialog('close');
                       	return false;
                       }
                   }, { //Save button
                       id: 'save_'+dialogId,
                       text: 'Ok',
                       click: function(){
                    	   if (callBackHandler){
                    		   SEVENJ.validateAndSubmitForm(dialogId, formId, actionUrl,callBackHandler);
                    	   }
                       }
                   }],
          close: function () {
       	   // $('body').css('overflow','scroll');
           	$(dialogId).dialog('close');
          },
          open: function( event, ui ) {
       	   //$('body').css('overflow','hidden');
       	    $(formId).validationEngine({'binded':false});
          }
       });
   },
   validateAndSubmitForm: function(dialogId, formId, actionUrl,callBackHandler){
	   	$(formId).validationEngine('hide');
	   	$(formId).validationEngine('detach');
	   	$(formId).validationEngine({'binded':false});
	   	form = $(formId);
	   	var validateSuccess = form.validationEngine('validate');
	   	if (!validateSuccess){
	   		return false;
	   	}
	   	$.ajax({
	   		 type: 'POST',
	            url: actionUrl,
	            data: form.serialize(),
	            success : function(data){
	           	 if (data.Result == RESULT_OK){
	           		 $(dialogId).dialog('close');
	           		 if (callBackHandler){
	           			 callBackHandler();
	           		 }
	           	 }else{
	           		 SEVENJ.showError(data.Message);
	           	 }
	            },
	            error : function(){
	           	 SEVENJ.showFatalError();
	            }
	        });
	 },
	 loadPoupContent: function(dialogId,url){
		 $.ajax({
	       		type: "GET",
	       		url : url,
	       		success : function(content) {
	       			$(dialogId).html(content);
	       			$(dialogId).dialog('open');
	       		},
	       		error : function(e) {
	       			SEVENJ.showFatalError();
	       		}
	       	});
	 },
	 loadTabContent: function(tabId, tabContentId, url){
		 $.ajax({
	       		type: "GET",
	       		url : url,
	       		success : function(content) {
	       			$(tabContentId).html(content);
	       			SEVENJ._selectToolPlanTab(tabId);
	       		},
	       		error : function(e) {
	       			SEVENJ.showFatalError();
	       		}
	       	});
	 },
	 // load content for price
	 loadTabContentForPricePlan: function(tabId, tabContentId, url)
	 {
		 $.ajax({
			 type: "GET",
			 url:url,
			 success : function(content){
				 $(tabContentId).html(content);
				 SEVENJ._selectPricePlanTab(tabId);
			 },
			 error: function(e){
				 SEVENJ.showFatalError();
			 }
		 });
		 
	 },
	 ajaxSubmit: function(formId,actionUrl,callback){
		 form = $(formId);
		 $.ajax({
	   		 type: 'POST',
	            url: actionUrl,
	            data: form.serialize(),
	            success : function(data){
	           	 if (data.Result == RESULT_OK){
	           		 if (callback){
	           			callback();
	           		 }
	           	 }else{
	           		 SEVENJ.showError(data.Message);
	           	 }
	            },
	            error : function(){
	           	 SEVENJ.showFatalError();
	            }
	        });
	 },
	 ajaxGet: function(actionUrl,parameters,callback){
		 $.ajax({
	   		 type: 'GET',
	            url: actionUrl,
	            data: parameters,
	            success : function(data){
	           		 if (callback){
	           			callback();
	           		 }
	            },
	            error : function(){
	           	 SEVENJ.showFatalError();
	            }
	        });
	 },
	 redirect: function(url){
		 window.location.href = url;
	 },
	 getToolPlanStatus: function(statusCode){
		 var value ='';
		 if (statusCode == '1'){
			 value = 'Approved';
		 }else if (statusCode == '0'){
			 value = 'Pending';
		 }else if (statusCode == '2'){
			 value = 'Rejected';
		 }
		 return value;
	 },
	 _isActiveTab: function(tabId){
		 return $(tabId).hasClass(activeMenuClass);
	 },
	 _unselectTab: function(tab){
		 $(tab.id).html('<a><span class="tab">' + tab.title + '</span></a>');
		 $(tab.id).removeClass(activeMenuClass);
	 },
	 _selectToolPlanTab: function(tabId){
		 var tabTitle = '';
		 for (var i = 0; i < toolPlanTabs.length; i++){
			 tab = toolPlanTabs[i];
			 if (tab.id != tabId){
				 if (SEVENJ._isActiveTab(tab.id)){
					 SEVENJ._unselectTab(tab);
				 }
			 }else{
				 tabTitle = tab.title;
			 }
		 }
		 $(tabId).html('<div><span class="tab">' + tabTitle + '</span></div>');
		 $(tabId).addClass(activeMenuClass);
	 },
	 //select  price plan tab
	_selectPricePlanTab: function(tabId){
		var tabTitle='';
		for(var j = 0; j < pricePlanTabs.length; j++)
			{
				tab=pricePlanTabs[j];
				if(tab.id !=tabId)
				{
					if(SEVENJ._isActiveTab(tab.id))
						{
							SEVENJ._unselectTab(tab);
						}
				}else{
						tabTitle=tab.title;
				}
			}
		 $(tabId).html('<div><span class="tab">' + tabTitle + '</span></div>');
		 $(tabId).addClass(activeMenuClass);
	},
	 validateAndSubmit: function(formId, actionUrl, callback){
			$(formId).validationEngine('hide');
			$(formId).validationEngine('detach');
			$(formId).validationEngine({'binded':false});
			var isValid = $(formId).validationEngine('validate');
			if (isValid){
				SEVENJ.ajaxSubmit(formId,actionUrl,callback);
			}
	 },
	 setupToolPlanTable: function(tableId, listActionUrl, showSelectCheckBox, currentUser){
		 $(tableId).jtable({
		        title: '',
		        paging : true,
				pagSize : 50,
				sorting : true,
				selecting: showSelectCheckBox, //Enable selecting
		        multiselect: showSelectCheckBox, //Allow multiple selecting
		        selectingCheckboxes: showSelectCheckBox, //Show checkboxes on first column
				/*defaultSorting : 'tableEntryCreationDate DESC',*/
		        messages: {
				    save:'Ok'
				},
		        actions: {
		            listAction: listActionUrl
		        },
		        fields: {
		        	checkOutUser:{
		        		title: '<img src="images/checked_in.gif"/>',
		        		display: function(data){
		        			if (data.record){
		        				//alert(data.record.checkOutUser);
		        				if(data.record.status == 1){
			        				if (data.record.checkOutUser == currentUser){
			        					return '<img src="images/checked_out.gif" class="state" onclick="showEditToolPlanPage(\'' + data.record.ID +'\');" alt="Edit this tool plan"/>';
			        				}else{
			        					return '<img src="images/checked_out_other.gif" onclick="viewToolPlanPage(\'' + data.record.ID +'\');" class="state" alt="View this tool plan"/>';
			        				}
		        				}else{
		        					return '<img src="images/checked_in.gif" class="state" alt="Check out and edit" onclick="SEVENJ.checkOutToolPlan(\'' + data.record.ID + '\')" />';
		        				}
		        			}
		        		},
		        		sorting: false,
		        		width: '1%'
		        	},
		            approveStatus: {
		            	title: 'Status',
		            	sorting: true,
		            	width: '5%',
		            	display: function(data){
		            		if (data){
		            			return SEVENJ.getToolPlanStatus(data.record.approveStatus);
		            		}
		            	}
		            },
		            ID: {
		            	key: true,
		            	title: 'PRG PLAN RATE REC',
		            	sorting: false,
		            	width: '5%',
		            	display: function(data){
		            		if (data.record){
		            			return '<a href="viewToolPlan.htm?toolPlanId=' + data.record.ID + '">' + data.record.originatingDestinationCode + '-' + data.record.terminatingDestinationCode + '</a>';
		            		}
		            	}
		            },
		            weekDefinitionCode: {
		                title: 'WEEK DFNTN CODE',
		                sorting: true,
		                width: '10%'
		            },
		            ratePeriodSequenceNumber:{
		            	title: 'RATE PERD SEQ NBR',
		                sorting: true,
		                width: '10%'
		            },
		            typeOfCall:{
		            	title: 'TYPE OF CALL',
		                sorting: true,
		                width: '10%'
		            },
		            speacialTypeOfCall:{
		            	title: 'SPCL CALL TYPE CD',
		                sorting: true,
		                width: '10%'
		            },
		            originatingDestinationCode: {
		                title: 'ORIG DEST CODE',
		                width: '10%'
		            },
		            originatingSatelliteCode: {
		                title: 'ORIG SATLT CODE',
		                sorting: true,
		                width: '10%'
		            },
		            terminatingDestinationCode: {
		                title: 'TERM DEST CODE',
		                sorting: true,
		                width: '10%'
		            },
		            terminatingSatelliteCode: {
		                title: 'TERM SATLT CODE',
		                sorting: true,
		                width: '10%'
		            },
		            rateCapSequenceNumber: {
		                title: 'RATE CAP SEQ NBR',
		                width: '10%',
		                sorting: true
		            },
		            customerSpecificRatingCode:{
		            	title: 'CUST SPFC RTG CODE',
		                width: '10%',
		                sorting: true
		            },
		            tableEntryEffectiveDate: {
		                title: 'TAB ENT EFFV DT',
		                display:function (data) {
		                    return moment(data.record.tableEntryEffectiveDate).format('YYYYMMDD');
		                },
		                width: '10%',
		                sorting: true
		            },
		            rateType: {
		                title: 'RATE TYPE',
		                width: '5%',
		                sorting: true
		            },
		            rate: {
		                title: 'RATE',
		                width: '5%',
		                sorting: true,
		                display: function (data) {
                            return (data.record.rate).toLocaleString('de-DE');
                        }
		            },
		            rateTimeUnit: {
		                title: 'RATE TIME UNIT',
		                width: '10%',
		                sorting: true
		            },
		            messageLevelPercentDiscount:{
		            	title: 'MSG LEV PCT DSCNT',
		                width: '10%',
		                sorting: true
		            },
		            capThresholdCode: {
		                title: 'CAP THRSHLD CODE',
		                width: '5%',
		                sorting: true
		            },
		            minimumTime:{
		            	title: 'MINIMUM TIME',
		                width: '10%',
		                sorting: true
		            },
		            incrementTime:{
		            	title: 'INCRMNT TIME',
		                width: '10%',
		                sorting: true
		            },
		            incrementTimeUnit:{
		            	title: 'INCRMNT TIME UNIT',
		                width: '10%',
		                sorting: true
		            },
		            rateValidationIndicator:{
		            	title: 'RATE VAL IND',
		                width: '10%',
		                sorting: true
		            },
		            tableEntryExpirationDate:{
		            	title: 'TAB ENT EXP DT',
		                width: '10%',
		                sorting: true
		            },
		            tableEntryExpirationDate: {
		                title: 'TAB ENT CRTN DT',
		                display:function (data) {
		                    return moment(data.record.tableEntryExpirationDate).format('YYYYMMDD');
		                },
		                width: '10%',
		                sorting: true
		            }
		        }
		    });
	 },
	 checkOutToolPlan: function(toolPlanId){
		 $.ajax({
	   		 type: 'POST',
	            url: 'checkOutToolPlan.htm',
	            data: 'toolPlanId=' + toolPlanId,
	            success : function(data){
	           	 if (data.Result == RESULT_OK){
	           		SEVENJ.redirect("editToolPlan.htm?toolPlanId="+toolPlanId);
	           	 }else{
	           		 SEVENJ.showError(data.Message);
	           	 }
	            },
	            error : function(){
	           	 SEVENJ.showFatalError();
	            }
	        });
	 },
	 back: function(){
		window.history.back();
	 },
	 getCurrentTabId: function(currentTab){
		 var tabId = "";
		 if (currentTab == '1'){
		  tabId = "#pricePlanTabId";
		 }else{
		  tabId = "#tabPendingPricePlan";
		 }
		return tabId;
	},
	redirectPricePlanPage: function(tabId, projectId,pricePlanCode){
		SEVENJ.redirect("viewProject.htm?projectId="+projectId);
		SEVENJ.loadTabContentForPricePlan(tabId,'#tabContent','viewPricePlanNew.htm?pricePlanCode=' + pricePlanCode);
	},
	gobackToViewPricePlan: function(){
		SEVENJ.redirect("forwardToViewPricePlan.htm");
	},
	redirectNewTab:function(url){
		window.open(url, '_blank');
	}
};