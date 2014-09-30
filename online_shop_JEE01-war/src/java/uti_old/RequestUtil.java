package util;

import static util.HtmlUtil.printTableBegin;
import static util.HtmlUtil.printTableEnd;
import static util.HtmlUtil.printTableRow;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

  public static void printRequestAttributes(HttpServletRequest request, PrintWriter out) throws ServletException,
    IOException {
    printTableBegin(out, "Request Attributes", "Attributname", "Attributwert");
    Enumeration<String> attributeNames = request.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      String attribName = (String) attributeNames.nextElement();
      Object attribValue = request.getAttribute(attribName);
      printTableRow(out, attribName, attribValue.toString());
    }
    printTableEnd(out);
  }

  public static void printRequestParameters(HttpServletRequest request, PrintWriter out) {
    Map<String, String[]> paramMap = request.getParameterMap();
    printTableBegin(out, "Request Parameter", "Parametername", "Parameterwert");
    for (String paramName : paramMap.keySet()) {
      printTableRow(out, paramName, Arrays.toString(paramMap.get(paramName)));
    }
    printTableEnd(out);
  }

  public static void printRequestParameters2(HttpServletRequest request, PrintWriter out) {
    printTableBegin(out, "Request Parameter", "Parametername", "Parameterwert");
    final Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String paramName = parameterNames.nextElement();
//      final String paramValue = request.getParameter(paramName);
      String[] paramValues = request.getParameterValues(paramName);
      printTableRow(out, paramName, Arrays.toString(paramValues));
    }
    printTableEnd(out);
  }

  public static void printRequestHeaders(HttpServletRequest request, PrintWriter out) {
    printTableBegin(out, "Request Headers", "Headername", "Headerwert");
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = (String) headerNames.nextElement();
      String headerValue = request.getHeader(headerName);
      printTableRow(out, headerName, headerValue);
    }
    printTableEnd(out);
  }

  public static void printRequestMethods(HttpServletRequest request, PrintWriter out) {
    printTableBegin(out, "Request Methods", "Method", "Output");
    printTableRow(out, "request.getMethod()", request.getMethod());
    printTableRow(out, "request.getParameter(\"nachname\")", request.getParameter("nachname"));
    final Enumeration<String> parameterNames = request.getParameterNames();
    printTableRow(out, "request.getPathInfo()", request.getPathInfo());
    printTableRow(out, "request.getRequestURI()", request.getRequestURI());
    printTableRow(out, "request.getRequestURL()", request.getRequestURL().toString());
    printTableRow(out, "request.getServletPath()", request.getServletPath());
    printTableRow(out, "request.getContextPath()", request.getContextPath());
    printTableEnd(out);
  }

  public static void printCookies(HttpServletRequest request, PrintWriter out) {
    printTableBegin(out, "Cookies", "Cookie Name", "Cookie Value");
    final Cookie[] cookies = request.getCookies();
    // Alternative (NICHT zu empfehlen: umständlich)
    // printTableRow(out, "request.getHeader(\"Cookie\")", request.getHeader("Cookie"));
    for (Cookie cookie : cookies) {
      printTableRow(out, cookie.getName(), cookie.getValue());
    }
    printTableEnd(out);
  }

  public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    Cookie namedCookie = null;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          namedCookie = cookie;
          break;
        }
      }
    }
    return namedCookie;
  }

}
