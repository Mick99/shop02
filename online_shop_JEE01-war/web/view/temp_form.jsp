<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : temp_form
    Created on : 28.09.2014, 16:13:25
    Author     : Mick_02
--%>

   <!-- Form Template start Ganzen Kopfbereich rausgenommen -->
         <form method="post" action="${root}/front/controller/save">
            
            <input type="hidden" name="id" value="${entity.id}"/>
            <p>
            <label for="name">name:</label>
            <input type="text" name="name" id="name" value="${entity.name}"/>
            </p>
            <p>
            <label for="price">price:</label>
            <input type="text" name="price" id="price"
                   value="<fmt:formatNumber value='${entity.price}' pattern='###,##0.00'/>"/>
            </p>
            <input type="submit" value="senden"/>
         </form>
   <!-- Form Template stop -->
