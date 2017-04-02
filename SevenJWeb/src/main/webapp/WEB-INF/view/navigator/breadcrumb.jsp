<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div>
    <a href="projectList.htm">Home</a> >
	<c:forEach var="entry" items="${sessionScope.currentBreadCrumb}">
		<c:choose>
			<c:when test="${entry.currentPage == true}">
			    <c:choose>
			    	<c:when test="${entry.label eq 'webSession.currentProject.projectName'}">
			    		${ webSession.currentProject.projectName }
			    	</c:when>
			    	<c:when test="${entry.label eq 'webSession.currentPricePlan.pricePlanName'}">
			    		${ webSession.currentPricePlan.pricePlanName }
			    	</c:when>
			    	<c:otherwise>
			    		${entry.label}
			    	</c:otherwise>
			    </c:choose>
			</c:when>
			<c:when test="${entry.label eq 'webSession.currentProject.projectName'}">
			     <a href="${entry.url}"> ${ webSession.currentProject.projectName }</a> >
			</c:when>
			<c:when test="${entry.label eq 'webSession.currentPricePlan.pricePlanName'}">
	    		<a href="${entry.url}"> ${ webSession.currentPricePlan.pricePlanName }</a> >
	    	</c:when>
			<c:otherwise>
					<a href="${entry.url}">${entry.label}</a> >
			</c:otherwise>
		</c:choose>
	</c:forEach>

</div>