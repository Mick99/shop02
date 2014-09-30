/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import persistence.Article;
import services.ArticleBean;
import services.ArticleServiceLocal;
import services.ArticleServiceRemote;

/**
 *
 * @author Mick_02
 */
public class ArticleController extends AbstractController {
	
	private final static String ARTICLE = "java:global/online_shop_JEE01/online_shop_JEE01-ejb/ArticleBean!" + ArticleServiceRemote.class.getName();
	
	private ArticleServiceRemote articleService = (ArticleServiceRemote) getBean(ARTICLE);;

//	@EJB
//	ArticleBean ab;
	public void allAction() throws ServletException, IOException {
		request.setAttribute("entitys", articleService.getAll());
		request.setAttribute("template", "article_all.jsp");
		forward("/view/standard.jsp");
	}
	
	public void newAction() throws ServletException, IOException {
		request.setAttribute("entity", new Article());
		request.setAttribute("template", "article_form.jsp");
		forward("/view/standard.jsp");
	}
	
	public void saveAction() throws ServletException, IOException {
		boolean isUpdate = true, hasParseError = false;
		Article article = new Article();
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			article.setId(id);
		} catch (NumberFormatException e) {
			isUpdate = false;
		}
		Double price = null;
		try {
			price = Double.parseDouble(request.getParameter("price"));
			article.setPrice(price);
		} catch (NumberFormatException e) {
			hasParseError = true;
			String mes = String.format("Error: Price input='%s'", request.getParameter("price"));
			request.setAttribute("message", mes);
		}
		if (!request.getParameter("name").isEmpty()) {
			article.setName(request.getParameter("name"));
		} else {
			hasParseError = true;
			String mes = String.format("Error: Name input='%s'", request.getParameter("name"));
			request.setAttribute("message", mes);
		}
		// ++Bean
		if (!hasParseError) {
			try {
				if (isUpdate) {
					articleService.updateExist(article);
				} else {
					articleService.insertNew(article);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// --Bean
		String head = "Speichern ";
		if (!hasParseError) {
			head += "erfolgreich";
		} else {
			head += "fehlgeschlagen";
		}
		request.setAttribute("headline", head);
		request.setAttribute("template", "message.jsp");
		forward("/view/standard.jsp");
	}
	
	@Override
	public void indexAction() throws ServletException, IOException {
		request.setAttribute("template", "action_not_found.jsp");
		forward("/view/standard.jsp");
		
	}
	
	private Object getBean(String jndiName) {
		Object bean = null;
		try {
			Context context = new InitialContext();
			bean = context.lookup(jndiName);
		} catch (NamingException ex) {
			Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return bean;
	}
	
}
