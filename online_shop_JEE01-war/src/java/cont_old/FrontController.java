/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Salah Lejmi<info@jexp.org>
 */
@WebServlet(name = "FrontController", 
            urlPatterns = {"/FrontController"},
            loadOnStartup = 3)
public class FrontController extends HttpServlet {

  private static final String ACTION_MAPPING_CONFIG_FILE = "actions.properties";
  private Properties actionMapping;
  // C R U D: Create, Read / Retrieve, Update, Delete
  @Override
  public void init() throws ServletException {
    System.out.println("====================> FrontController: init() ...");
   // actionMapping wird aus der Datei "actions.properties" geladen
    try {
      //////////     todo <------------> Action-Klasse
      initialize();
    } catch (IOException ex) {
      Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    System.out.println("====================> FrontController:processRequest() ...");
    String todo = request.getParameter("todo");
    Action action = getAction(todo);
    action.execute(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
  }

  public Action getAction(String todo) { 
    // actions.properties:
    // artikelliste=controller.ArtikelListeAction 
    // d.h. wenn todo=artikelliste --> return new controller.ArtikelListeAction();
    
    String actionClassName = actionMapping.getProperty(todo);
    Action action = null;

    try {
      Class<Action> actionClass = (Class<Action>) Class.forName(actionClassName);
      action = actionClass.newInstance(); // action = new controller.ArtikelListeAction());
    } catch (Exception ex) {
      Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return action;
  }

  private void initialize() throws IOException {
    actionMapping = new Properties();
    InputStream in = getClass().getResourceAsStream(ACTION_MAPPING_CONFIG_FILE); // actions.properties
    actionMapping.load(in);
    in.close();
  }

}
