/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Article;

/**
 *
 * @author Mick_02
 */
@Stateless
public class ArticleBean implements ArticleServiceLocal, ArticleServiceRemote {

  @PersistenceContext(unitName = "online_shop_JEE01-ejbPU")
  EntityManager em;

  @Override
  public List<Article> getAll() {
    return new Article().findAll(em);
  }

  @Override
  public void insertNew(Article article) {
    System.out.println("BEAN insertNew:" + article);
    em.persist(article);
  }

  @Override
  public void updateExist(Article article) {
    System.out.println("BEAN updateExist:" + article);
    em.merge(article);
  }

  private void test() {

  }

  private void test2() {

  }

  private void test3() {

  }

}
