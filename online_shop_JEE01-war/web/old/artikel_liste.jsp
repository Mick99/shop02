<%-- 
    Document   : index
    Created on : 15.05.2014, 10:03:41
    Author     : Salah Lejmi<info@jexp.org>
--%>
<%--
<%@page import="model.Artikel"%>
<%@page import="java.util.List"%>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kapitel 4</title>
    </head>
    <body>

        <jsp:include page="header.jsp" />
        <h1>Online Shop: Artikelliste</h1>
        
        
        
<!--        Artikel 1: <a href="FrontController?todo=addArtikel&id=1">zum Warenkorb hinzufügen</a> <br/>
        Artikel 2: <a href="FrontController?todo=addArtikel&id=2">zum Warenkorb hinzufügen</a> <br/>
        Artikel 3: <a href="FrontController?todo=addArtikel&id=3">zum Warenkorb hinzufügen</a> <br/>
        Artikel 4: <a href="FrontController?todo=addArtikel&id=4">zum Warenkorb hinzufügen</a> <br/>-->

     <%--   
       <% 
			
          List<Artikel> artikelListe = (List<Artikel>) request.getAttribute("artikelListe"); 
          for(Artikel artikel : artikelListe) {
            // <a href="FrontController?todo=addArtikel&id=2">Zum Warenkorb hinzufügen</a>
            String link = String.format("<a href=\"FrontController?todo=addArtikel&id=%d\">Zum Warenkorb hinzufügen</a>", artikel.getId());
            out.println(artikel + " " + link + "<br/>");
          }
          
          
          Artikel artikel = (Artikel) request.getAttribute("neuerArtikel");
          if (artikel != null) {
            out.println("<hr/>");
            out.println("<div style='color:green'>" + artikel + " wurde erfolgreich angelegt</div>");
          }
        %>
		 --%>
		 <h3>Mein TEST</h3> 

    </body>
</html>
