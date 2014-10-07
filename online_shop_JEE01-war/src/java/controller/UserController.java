/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.UserServiceRemote;
import java.io.IOException;
import javax.servlet.ServletException;
import persistence.User;
import service.UserBean;
import static logic.BeanHelper.*;
import static util.LoggerHelper.*;

/**
 *
 * @author Mick_02
 */
public class UserController extends AbstractController {
  // Weiss derzeit nicht sicher ob "private final UserServiceRemote" gut geht, weil Wrapper?
  private UserServiceRemote userService;// = (UserServiceRemote) getBean(UserBean.class, UserServiceRemote.class);

    @Override
  public Class getBean() {
    return UserBean.class;
  }
  @Override
  public Class getRemote() {
    return UserServiceRemote.class;
  }
  @Override
  public void setProxy(Object proxy) {
    userService = (UserServiceRemote) proxy;
  }

  public void loginAction() throws ServletException, IOException {
    String username = request.getParameter("username");
    User user = userService.getUserIfExists(username);
    msgLog.trace("Username: " + username);
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
