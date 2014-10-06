/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.List;
import persistence.User;

/**
 * Create ..Controller for the same session only once.
 * 
 * @author Mick_02
 */
public class ControllerToUserLink {
  private User user;
  // Problem !! I need AbstractController not Object but can not bind -war modul, why?
  private List<Object> controllers;
  
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public List<Object> getControllers() {
    return controllers;
  }
  public void setControllers(Object controller) {
    if (controllers == null) {
      controllers = new ArrayList<>();
    }
    controllers.add(controller);
  }
}
