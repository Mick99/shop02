package util;

import static util.HtmlUtil.printTableBegin;
import static util.HtmlUtil.printTableEnd;
import static util.HtmlUtil.printTableRow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ContextUtil {
  
  public static void printContextAttributes(ServletContext context, PrintWriter out)
      throws ServletException, IOException {
    printTableBegin(out, "Context Attributes","Attributname","Attributwert");
    Enumeration<String> attributeNames = context.getAttributeNames();
    while (attributeNames.hasMoreElements()) {
      String attribName = (String) attributeNames.nextElement();
      Object attribValue = context.getAttribute(attribName);
      printTableRow(out, attribName, attribValue.toString());
    }
    printTableEnd(out);
  }

  public static void printContextParameters(ServletContext context, PrintWriter out)
      throws ServletException, IOException {
    printTableBegin(out, "Context-Parameters","Parametername","Parameterwert");
    Enumeration<String> initParamNames = context.getInitParameterNames(); 
    while (initParamNames.hasMoreElements()) {
      String initParamName = (String) initParamNames.nextElement();
      Object initParamValue = context.getInitParameter(initParamName);
      printTableRow(out, initParamName, initParamValue.toString());
    }
    printTableEnd(out);
  }
}
