/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.UserServiceRemote;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.User;

/**
 *
 * @author Mick_02
 */
@Stateless
@Remote(UserServiceRemote.class)
public class UserBean {
//  public static final Logger msg = LogManager.getLogger("MSG");
  
  @PersistenceContext(unitName = "online_shop_JEE01-ejbPU")
  EntityManager em;
  // Mal sehen ob ich richtig liege?
//  @Resource
//  User user;

  public User getUserIfExists(String checkUsername) {
    Query q = em.createQuery("select u from User u where u.username = :checkUsername");
    q.setParameter("checkUsername", checkUsername);
    try {
      User user = (User) q.getSingleResult();
//      msg.info(user.toString());
      return user;
      // This are special exceptions...
    } catch (NoResultException | NonUniqueResultException e) {
      return null;
      //... here Timeout, Locks,etc.
    } catch (Exception e) {
      return null;
    }
  }
}
