<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : temp_single
    Created on : 28.09.2014, 16:18:56
    Author     : Mick_02
--%>

<p>
   Id: ${entity.id}<br />
   Name: ${entity.name}<br /> 
   Price: <fmt:formatNumber value='${entity.price}' pattern='###,##0.00'/> EURO<br />

   <a href="${root}/front/controller/update/${entity.id}">EDIT</a>
   <a href="${root}/front/controller/remove/${entity.id}">REMOVE</a>

</p>

