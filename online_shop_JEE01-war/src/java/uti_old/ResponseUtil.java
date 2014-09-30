package util;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import static util.HtmlUtil.printTableBegin;
import static util.HtmlUtil.printTableEnd;
import static util.HtmlUtil.printTableRow;

public class ResponseUtil {
  
  public static void printResponseHeaders(HttpServletResponse response, PrintWriter out)
      throws ServletException, IOException {
    printTableBegin(out, "Response Headers","Headername","Headerwert");
    Collection<String> headerNames = response.getHeaderNames();
    
    for (String headerName : headerNames) {
      final Collection<String> headerValues = response.getHeaders(headerName);
      printTableRow(out, headerName, headerValues.toString());
    }
    printTableEnd(out);
  }

  public static void printResponseMethods(HttpServletResponse response, PrintWriter out)
      throws ServletException, IOException {
    printTableBegin(out, "Response Methods", "Method", "Output");
    printTableRow(out, "response.getCharacterEncoding()", response.getCharacterEncoding());
    printTableRow(out, "response.getContentType()", response.getContentType());
    printTableRow(out, "response.getBufferSize()", response.getBufferSize() + "");
    printTableRow(out, "response.getLocale()", response.getLocale().toString());
    printTableRow(out, "response.getStatus()", response.getStatus() + "");
    printTableEnd(out);
  }
}
