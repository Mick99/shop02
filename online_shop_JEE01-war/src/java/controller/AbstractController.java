package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Helper;

//import de.autoverwaltung.model.AbstractMapper;

public abstract class AbstractController {
   
   protected ServletContext application;
   protected HttpServletRequest request;
   protected HttpServletResponse response;
   
   public void init(ServletContext application, HttpServletRequest request, HttpServletResponse response) {
      this.application = application;
      this.request = request;
      this.response = response;
			this.request.setAttribute("title", Helper.getControllerName());
   }
   
   protected void forward(String target) throws ServletException, IOException {
      RequestDispatcher dispatcher = application.getRequestDispatcher(target);
      dispatcher.forward(request, response);
      
   }
   
   // Logic changed, true (Filter is on) is default behavior don't show rows with deleted are true
   // and false means show all rows, turn filter off at -model.AbstractMapper.
	 /*
   protected void filterAction(AbstractMapper<?> objMapper) throws ServletException, IOException {
   	String filter = request.getParameter ("deletedFilter");
   	
   	if (filter == null)
   		objMapper.setDeletedFilter (false);
   	else
   		objMapper.setDeletedFilter (true);
   }
	 */

   public abstract void indexAction() throws ServletException, IOException;
}
