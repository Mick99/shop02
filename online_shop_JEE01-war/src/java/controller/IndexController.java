package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

public class IndexController extends AbstractController {

   @Override
   public void indexAction() throws ServletException, IOException {
//      PrintWriter out = response.getWriter();
//      out.println("IndexController: Wat that");
      request.setAttribute("template", "test.jsp");
      forward("/view/standard.jsp");
   }
   
   
   
}
