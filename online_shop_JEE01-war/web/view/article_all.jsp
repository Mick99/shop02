<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
    Document   : temp_all
    Created on : 28.09.2014, 16:22:49
    Author     : Mick_02
--%>


<%-- <jsp:include page="filter.jsp" /> --%>

<h2> Artikel-Liste </h2>
<form method="post" action="/online_shop_JEE01-war/front/basket/finishorder">
  <input type="submit" value="Bestellung abschließen"/>
</form>

<c:forEach items="${entitys}" var="entity">
  <%@include file="article_single.jsp"%>
</c:forEach>


