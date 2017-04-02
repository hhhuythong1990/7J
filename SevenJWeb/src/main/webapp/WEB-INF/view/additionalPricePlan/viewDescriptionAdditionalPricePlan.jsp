<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/window.css">
<link rel="stylesheet" type="text/css" href="css/MyStyle.css">
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
			<c:if test="${not empty viewDescriptionAdditionalPricePlan}">
				<p class="title-des">
					${viewDescriptionAdditionalPricePlan.pricePlanCode} - ${viewDescriptionAdditionalPricePlan.pricePlanName}
				</p>
				${viewDescriptionAdditionalPricePlan.description}
			</c:if>
			
		</div>
	</div>
