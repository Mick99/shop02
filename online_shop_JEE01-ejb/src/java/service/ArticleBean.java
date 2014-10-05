/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.ArticleServiceRemote;
import interfaces.ArticleServiceLocal;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Article;

/**
 *
 * @author Mick_02
 */
@Stateless
@LocalBean
public class ArticleBean implements ArticleServiceRemote { // ArticleServiceLocal,

  @PersistenceContext(unitName = "online_shop_JEE01-ejbPU")
  EntityManager em;

  @Override
  public List<Article> getAll() {
    return new Article().findAll(em);
  }

  @Override
  public void insertNew(Article article) {
    em.persist(article);
  }

  @Override
  public void updateExists(Article article) {
    em.merge(article);
  }
  public Article getArticle(Long findById) {
    return em.find(Article.class, findById);
  }

}
