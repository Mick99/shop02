package util;

import static util.HtmlUtil.printTableBegin;
import static util.HtmlUtil.printTableEnd;
import static util.HtmlUtil.printTableRow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

public class PageUtil {
  
  public static void printPageAttributes(PageContext pageContext, PrintWriter out)
      throws ServletException, IOException {
    printTableBegin(out, "Page Attributes","Attributname","Attributwert");
    Enumeration<String> attributeNames = pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE);
    while (attributeNames.hasMoreElements()) {
      String attribName = (String) attributeNames.nextElement();
      Object attribValue = pageContext.getAttribute(attribName);
      printTableRow(out, attribName, attribValue.toString());
    }
    printTableEnd(out);
  }

}
