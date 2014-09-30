package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtil {

  private static final String APPLICATION_OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";
  private static final String CONTENT_DISPOSITION_HEADER = "Content-disposition";
  private static final String ATTACHMENT_FILENAME_FORMAT_STRING = "attachment; filename=\"%s\"";

  public static void include(HttpServletRequest request, HttpServletResponse response, String url)
    throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher(url);
    rd.include(request, response);
  }

  public static void forward(HttpServletRequest request, HttpServletResponse response, String url)
    throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher(url);
    rd.forward(request, response);
  }

  /**
   * Forwards to an external application in the same JVM (Container)
   *
   * @param request
   * @param response
   * @param otherContextName
   * @param url
   * @throws ServletException
   * @throws IOException
   */
  public static void forward(HttpServletRequest request, HttpServletResponse response, String otherContextName,
    String url) throws ServletException, IOException {
    ServletContext myContext = request.getServletContext();
    ServletContext otherContext = myContext.getContext(otherContextName);
    RequestDispatcher otherRd = otherContext.getRequestDispatcher(url);
    // forward an /ch01/Servlet1
    otherRd.forward(request, response);
  }

  public static void forward2(HttpServletRequest request, HttpServletResponse response, String url)
    throws ServletException, IOException {

    ServletContext context = request.getServletContext();
    // url: muss eine absolute Pfadangabe sein.
    RequestDispatcher rd = context.getRequestDispatcher(url);
    rd.forward(request, response);
  }

  /**
   * @param request
   * @param response
   * @param folderName
   * @param fileName
   * @throws IOException
   */
  public static void download(HttpServletRequest request, HttpServletResponse response, String folderName, String fileName) throws IOException {
    // set headers for the content type and the file name
    setHeaders(request, response, fileName);
    ServletContext context = request.getServletContext();
    String fullDownloadFileName = folderName + fileName;
    System.out.println("Angeforderter Dateiname: " + fullDownloadFileName);
    // context.getResourceAsStream("/WEB-INF/downloads/file.ext")
    InputStream is = context.getResourceAsStream(fullDownloadFileName);
    OutputStream os = response.getOutputStream();
    IOTools.copy(is, os);
    os.close();
    is.close();
  }

  private static void setHeaders(HttpServletRequest request, HttpServletResponse response, String fileName) {
    /////////////////////////// Header for content type ///////////////////////////
//    response.setContentType(APPLICATION_OCTET_STREAM_CONTENT_TYPE);
    final ServletContext context = request.getServletContext();
    String mimeType = context.getMimeType(fileName);
    System.out.println("MimeType: " + mimeType); // text/html, text/plain, application/pdf, ....
    response.setContentType(mimeType);
    /////////////////////////// Header for the file name ///////////////////////////
    String disposition = String.format(ATTACHMENT_FILENAME_FORMAT_STRING, fileName);
    System.out.println("Header: " + CONTENT_DISPOSITION_HEADER + "=" + disposition);
    response.setHeader(CONTENT_DISPOSITION_HEADER, disposition);

  }

  /**
   *
   * @param request
   * @param response
   * @return passendes Session Objekt basiert auf entweder Cookies oder URL-Rewriting.
   * @throws ServletException
   * @throws IOException
   */
  public static HttpSession getSessionWithSuitableMechanism(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    // getSession(false) --> gibt eine vorhandene Session zurueck (wenn es keine gibt --> null)
    HttpSession session = request.getSession(false);
    if (session == null) { //
      // Entweder 1. Aufruf ODER Cookies deaktiviert
      // getSession(true) --> gibt eine vorhandene Session zurueck (wenn es keine gibt --> erstellt eine neue)
      session = request.getSession(true); // session erstellt (123, 456)
      String redirect = request.getParameter("redirect");
      if (redirect == null) { // 1. Abfrage
        response.sendRedirect(request.getRequestURI() + "?redirect=true"); // Redirect zu "/FC?redirect=true"
      } else { // 2. Abfrage --> Definitiv sind Cookies deaktiviert
        // Umschalten auf URL-Rewriting
        String rewrite = response.encodeRedirectURL(request.getRequestURI()); // --> "/FC;jsessionid=456"
        response.sendRedirect(rewrite);
      }
    }
    // session.setMaxInactiveInterval(60 * 30); // Angabe in Sekunden: hier sind es 30 Minuten
    return session;
  }

  /**
   *
   * @param attributeMap
   * @return the attributes as a string: attrib1="value1" attrib2="value2" ...
   */
  public static String getAttributeString(Map<String, Object> attributeMap) {
    final String ATTRIB_FORMAT_STRING = "%s=\"%s\" ";
    // attribName="attribValue" 
    String ausgabe = "";
    for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
      ausgabe += String.format(ATTRIB_FORMAT_STRING, entry.getKey(), entry.getValue());
    }
    return ausgabe;
  }

  public static void doGetTemplate(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    doGetTemplate(request, response, null);
  }

  public static void doGetTemplate(HttpServletRequest request, HttpServletResponse response, String headerFile)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      /*
       * TODO output your page here. You may use following sample code.
       */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet MyServlet</title>");
      out.println("</head>");
      out.println("<body>");
      if (headerFile != null) {
        include(request, response, headerFile);
      }
//      out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    } finally {
      //out.close(); // mit close() wird das Byte-Stream zum Client abgeschickt
    }
//    System.out.println("Hallo, ich bin noch da.");
  }

  private void test() {
    double[] zahlen = {12, 14, 5, 9};
    double minZahl = zahlen[0];
    double maxZahl = zahlen[0];

    for (int i = 0; i < zahlen.length; i++) {
      double meineZahl = zahlen[i];
      if (zahlen[i] % 2 == 1) {
        System.out.println(meineZahl + " - ");
      }

    }
  }

  public static Object getAttribute(ServletRequest request, String attributeName, Class attributeClass, boolean createIfNotExist) {
    Object attribute = request.getAttribute(attributeName);
    if (attribute == null && createIfNotExist) {
      try {
        attribute = attributeClass.newInstance();
      } catch (InstantiationException ex) {
        Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
      request.setAttribute(attributeName, attribute);
    }
    return attribute;
  }

  public static Object getAttribute(HttpSession session, String attributeName, Class attributeClass, boolean createIfNotExist) {
    Object attribute = session.getAttribute(attributeName);
    if (attribute == null && createIfNotExist) {
      try {
        attribute = attributeClass.newInstance();
      } catch (InstantiationException ex) {
        Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
      session.setAttribute(attributeName, attribute);
    }
    return attribute;
  }

  public static Object getAttribute(ServletContext application, String attributeName, Class attributeClass, boolean createIfNotExist) {
    Object attribute = application.getAttribute(attributeName);
    if (attribute == null && createIfNotExist) {
      try {
        attribute = attributeClass.newInstance();
      } catch (InstantiationException ex) {
        Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
      }
      application.setAttribute(attributeName, attribute);
    }
    return attribute;
  }
}
