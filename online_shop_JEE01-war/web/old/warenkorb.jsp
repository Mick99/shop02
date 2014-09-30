<%@page import="controller.ActionUtil"%>
<%@page import="model.Artikel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online-Shop: Warenkorb</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <h1>Online-Shop: Warenkorb*</h1>
        <%
          List<Artikel> warenkorb = ActionUtil.getWarenkorb(request, response);
          if (warenkorb.size() != 0) {
            double gesamtPreis = 0.0;
            for (Artikel artikel : warenkorb) {
              // <a href="FrontController?todo=addArtikel&id=2">Zum Warenkorb hinzufügen</a>
              out.println(artikel + " " + artikel + "<br/>");
              gesamtPreis += artikel.getPreis();
            }
            out.println("<hr/>");
            out.println("Gesamtpreis: " + gesamtPreis + " €");
          } else {
            out.println("- Ihr Warenkorb ist leer -");
          }
        %>


    </table>
</body>
</html>