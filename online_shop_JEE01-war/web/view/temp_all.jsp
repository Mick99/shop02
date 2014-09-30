<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
    Document   : temp_all
    Created on : 28.09.2014, 16:22:49
    Author     : Mick_02
--%>


<%-- <jsp:include page="filter.jsp" /> --%>

<h2> Entity </h2>

<c:forEach items="${entitys}" var="entity">
 	<c:if test="${entity.deleted == false}">
		<%@include file="temp_single.jsp"%>
	</c:if>
	<c:if test="${entity.deleted == true}">
		<%@include file="tempdeleted_single.jsp"%>
	</c:if>
</c:forEach>


