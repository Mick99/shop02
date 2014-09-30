package util;

import static util.HtmlUtil.printTableBegin;
import static util.HtmlUtil.printTableEnd;
import static util.HtmlUtil.printTableRow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtil {

  public static HttpSession getSessionWithSuitableMechanism(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    System.out.println("=========================> Decision ...");
    // getSession(false) --> gibt eine vorhandene Session zurueck (wenn es keine gibt --> null)
    HttpSession session = request.getSession(false);
    String firstUrl = request.getRequestURI(); // --> http://localhost:port/ctx/MyServlet
    if (session == null) { //
      System.out.println("=========================> session = null");
      // Entweder 1. Aufruf ODER Cookies deaktiviert
      // getSession(true) --> gibt eine vorhandene Session zurueck (wenn es keine gibt --> erstellt eine neue)
      session = request.getSession(true); // session erstellt (123, 456)
      String redirect = request.getParameter("redirect");
      if (redirect == null) { // 1. Aufruf
        System.out.println("firstUrl: " + firstUrl + "?redirect=true");
        response.sendRedirect(firstUrl + "?redirect=true"); // Redirect zu "/FC?redirect=true"
      } else { // 2. Aufruf --> Definitiv sind Cookies deaktiviert
        // Umschalten auf URL-Rewriting
        String rewrite = response.encodeRedirectURL(firstUrl); // --> "/FC;jsessionid=456"
        System.out.println("Cookies sind deaktiviert --> URL-Rewriting: econdUrl: " + rewrite);
        response.sendRedirect(rewrite);
      }
      return null; // !!! damit der Rest vom Servlet nicht aufgefÃ¼hrt wird
    } else { // session != null --> Entweder Cookie oder URL-Rewriting wurde benutzt
      if (request.getParameter("redirect") != null) { // Cookies
        System.out.println("Cookies in Action: " + session.getId());
        System.out.println("firstUrl: " + firstUrl);
        response.sendRedirect(firstUrl); // Redirect zu "/FC"
        return null;
      } else { // Rewriting
        Cookie sessionCookie = RequestUtil.getCookieByName(request, "JSESSIONID");
        if (sessionCookie == null) {
          System.out.println("URL-Rewriting in Action: --> " + session.getId());
        }
      }
    }
    // session.setMaxInactiveInterval(60 * 30); // Angabe in Sekunden: hier sind es 30 Minuten
    return session;
  }

  public static void printSessionAttributes(HttpSession session, PrintWriter out)
    throws ServletException, IOException {
    printTableBegin(out, "Session Attributes", "Attributname", "Attributwert");
    Enumeration<String> attributeNames = session.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      String attribName = (String) attributeNames.nextElement();
      Object attribValue = session.getAttribute(attribName);
      printTableRow(out, attribName, attribValue.toString());
    }
    printTableEnd(out);
  }

  public static void printSessionMethods(HttpSession session, PrintWriter out)
    throws ServletException, IOException {
    printTableBegin(out, "Session Methods", "Method", "Output");
    printTableRow(out, "session.toString()", session.toString());
    printTableRow(out, "session.getId()", session.getId());
    printTableRow(out, "session.getCreationTime()", session.getCreationTime() + " ms");
    printTableRow(out, "session.getLastAccessedTime()", session.getLastAccessedTime() + " ms");
    printTableRow(out, "Lebensdauer ", (session.getLastAccessedTime() - session.getCreationTime()) / 60000.0 + " min");
    printTableRow(out, "session.getMaxInactiveInterval()", session.getMaxInactiveInterval() / 60 + " min");
    printTableRow(out, "session.isNew()", session.isNew());
    printTableEnd(out);
  }

  private String getSessionAge(HttpSession session) {
    final long dauer = session.getLastAccessedTime() - session.getCreationTime();
    String output = "";

    return output;
  }
}
