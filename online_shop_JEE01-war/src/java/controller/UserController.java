/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.UserServiceRemote;
import java.io.IOException;
import javax.servlet.ServletException;
import static logic.BeanHelper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.User;
import service.UserBean;

/**
 *
 * @author Mick_02
 */
public class UserController extends AbstractController {
  public static final Logger msg = LogManager.getLogger("MSG");
  public static final Logger exc = LogManager.getLogger("EXC");
  final private UserServiceRemote userService = (UserServiceRemote) getBean(UserBean.class, UserServiceRemote.class);

  public void loginAction() throws ServletException, IOException {
    String username = request.getParameter("username");
    User user = userService.getUserIfExists(username);
    msg.trace("Username: " + username);
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
