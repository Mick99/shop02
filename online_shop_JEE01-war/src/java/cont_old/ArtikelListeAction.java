/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ServletUtil;

/**
 *
 * @author User
 */
public class ArtikelListeAction implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 1. Parameter auslesen, validieren und entprechende Objekte erstellen
    
    // 2. Modell aufrufen
//    ArtikelService artikelService = new ArtikelService(); // nicht optimal
//    List<Artikel> artikelListe = artikelService.getAllArtikel();
	  System.out.printf("Test%n");
    Cookie myCookie = new Cookie("myCookie", )
    // 3. Request Attribute anlegen und Weiterleitung vornehmen
    request.setAttribute("myCookie", myCookie);
    ServletUtil.forward(request, response, "artikel_liste.jsp");
    
  }
  
}
