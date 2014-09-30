<%@page import="model.Artikel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online-Shop: Alle Warenkörbe</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h1>Online-Shop: Alle Warenkörbe</h1>
        <%
          List<HttpSession> sessionList = (List<HttpSession>) application.getAttribute("sessionList");
          for (HttpSession actualSession : sessionList) {
            List<Artikel> warenkorb = (List<Artikel>) actualSession.getAttribute("warenkorb");
            out.println("<h2>Session-ID: " + actualSession.getId() + "</h2>");
            if (warenkorb != null && warenkorb.size() != 0) {
              double gesamtPreis = 0.0;
              for (Artikel artikel : warenkorb) {
                // <a href="FrontController?todo=addArtikel&id=2">Zum Warenkorb hinzufügen</a>
                out.println(artikel + " " + artikel + "<br/>");
                gesamtPreis += artikel.getPreis();
              }
              out.println("-----<br/>");
              out.println("Gesamtpreis: " + gesamtPreis + " €");
              out.println("<hr/>");
            } else {
              out.println("- Der Warenkorb ist leer -");
            }
          }

        %>


    </table>
</body>
</html>