<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <link rel="stylesheet" href="${root}/css/style.css"/>
      <title>${title}</title>
   </head>
   <body>
      <div id="wrapper">

         <%@include file="navi.jsp"%>
         
         <h1>${headline}</h1>

         <jsp:include page="${template}" />

      </div>    
   </body>
</html>
