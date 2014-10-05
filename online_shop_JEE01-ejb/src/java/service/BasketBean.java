/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.ArticleServiceLocal;
import interfaces.BasketServiceRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Article;
import persistence.BasketTest;
import persistence.User;
import util.LoggerHelper;
import static util.LoggerHelper.*;

/**
 *
 * @author Mick_02
 */
@Stateful
@Remote(BasketServiceRemote.class)
public class BasketBean {
  @PersistenceContext(unitName = "online_shop_JEE01-ejbPU")
  EntityManager em;
  @EJB
  ArticleBean artBean;
  private List<BasketTest> basketLines;
  
  public List<BasketTest> getAll() {
    return em.createQuery("select bas from BasketTest bas order by id").getResultList();
  }
  public void insertNew(BasketTest basketLine) {
    addNewLine(basketLine);
  }
  public void insertNew(Long articleId, User user) {
    Article art = artBean.getArticle(articleId);
    BasketTest basketLine = new BasketTest(art, user);
    addNewLine(basketLine);
  }
  // Dummy, del as soon as posible
  public void insertNew(Long articleId) {
    Article art = artBean.getArticle(articleId);
    BasketTest basketLine = new BasketTest(art);
//    System.out.println(basketLine);
    addNewLine(basketLine);
  }
  // Persist all order-lines
  public void finishOrder() {
//    System.out.println("FINISH");
    for (BasketTest basketLine : basketLines) {
      em.persist(basketLine);
//      msgLog.trace("Order line: %s%n", basketLine);
    }
    // If persist OK than kill
    basketLines = null;
  }
  private void addNewLine(BasketTest basketLine) {
    // Add line immeditly uncomment these two lines.
//    em.persist(basketLine);
//    return;
    if (basketLines == null) {
      basketLines = new ArrayList<>();
    }
    basketLines.add(basketLine);
  }
}
