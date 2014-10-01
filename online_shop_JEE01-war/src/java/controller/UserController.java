/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.ArticleServiceRemote;
import interfaces.UserServiceRemote;
import java.io.IOException;
import javax.servlet.ServletException;
import static logic.BeanHelper.getBean;
import persistence.User;

/**
 *
 * @author Mick_02
 */
public class UserController extends AbstractController {

  private final static String UserJNDI = "java:global/online_shop_JEE01/online_shop_JEE01-ejb/UserBean!" + UserServiceRemote.class.getName();

  private UserServiceRemote userService = (UserServiceRemote) getBean(UserJNDI);

  public void loginAction() throws ServletException, IOException {
    String username = request.getParameter("username");
    User user = userService.getUserIfExists(username);
    System.out.println("Username: " + username);
    if (user != null) {
      request.setAttribute("headline", user.getUsername());
      request.setAttribute("message", ".. und nun ??");
      request.setAttribute("template", "message.jsp");
      forward("/view/standard.jsp");
    } else {
      forward("/view/loginfailed.jsp");
    }
  }

  @Override
  public void indexAction() throws ServletException, IOException {
    request.setAttribute("template", "action_not_found.jsp");
    forward("/view/standard.jsp");
  }

}
