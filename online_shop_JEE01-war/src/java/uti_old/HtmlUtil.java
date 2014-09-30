package util;

import java.io.PrintWriter;

public class HtmlUtil {
  
  public static void printH1(PrintWriter out, String title) {
    out.println("<h1>" + title + "</h1>" );
  }
  public static void printH2(PrintWriter out, String title) {
    out.println("<h2>" + title + "</h2>" );
  }
  public static void printTableBegin(PrintWriter out, String title, String linkeBeschriftung, String rechteBeschriftung) {
    printH1(out, title);
    out.println("<table style='font-family:monospace;font-size:16px;' border='1px' bordercolor='green' cellspacing='0' cellpadding='5'>" );
    printTableHeader(out, linkeBeschriftung, rechteBeschriftung);
  }
  public static void printTableEnd(PrintWriter out) {
    out.println("</table>" );
    out.println("<hr/>" );
  }
  public static void printTableRow(PrintWriter out, Object links, Object rechts) {
    out.println("<tr><td>" + links  + "&nbsp;</td>" );
    out.println("    <td>" + (links.toString().contains("MergedWebXml")?"Merged web.xml ...................":rechts) + "&nbsp;</td></tr>" );
  }
  public static void printTableHeader(PrintWriter out, Object links, Object rechts) {
    out.println("<tr><th>" + links.toString()  + "&nbsp;</th>" );
    out.println("    <th>" + rechts.toString() + "&nbsp;</th></tr>" );
  }

}
