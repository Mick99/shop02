/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AbstractController;
import controller.IndexController;
import logic.Helper;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.SessionTrackerBean;
import static util.LoggerHelper.*;

@WebServlet(name = "FrontController", urlPatterns = {"/front/*"})
//@WebServlet(name = "FrontController", urlPatterns = {"/front/*", "/admin/*"})

public class FrontController extends HttpServlet {

	/**
	 * Compiler warning.
	 */
//	private static final long serialVersionUID = 1L;
  @EJB
  SessionTrackerBean sessionTracker;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("root", request.getContextPath());
		
		AbstractController controller = getController(request);
		controller.init(getServletContext(), request, response);
		Method action = getControllerAction(request, controller);
		try {
			action.invoke(controller);
		} catch (Exception e) {
      excLog.info("Method not found: %s [in %s]", getControllerAction(request, controller).toString(), controller.toString());
			controller.indexAction();
		}
	}

	private AbstractController getController(HttpServletRequest request) {
		try {
//      sessionTracker.isNewSession(request); // Test
      // newInstance is the problem
			return (AbstractController) getControllerClass(request).newInstance();
		} catch (Exception e) {
			return new IndexController();
		}
	}

	private Class getControllerClass(HttpServletRequest request) {
		Class controller = null;
		String className = Helper.getFromRequest(request, Helper.Part.CONTROLLER);
    msgLog.trace("className: %s", className);
		try {
			controller = Class.forName(className);
		} catch (ClassNotFoundException e) {
			controller = IndexController.class;
		}
		return controller;
	}

	private Method getControllerAction(HttpServletRequest request, AbstractController controller) {

		Method action = null;
		String methodName = Helper.getFromRequest(request, Helper.Part.ACTION);

		try {
			action = controller.getClass().getMethod(methodName);
		} catch (NoSuchMethodException e) {

		}
		return action;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
