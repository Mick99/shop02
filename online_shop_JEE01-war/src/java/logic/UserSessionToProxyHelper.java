/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import controller.AbstractController;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mick_02
 */
public class UserSessionToProxyHelper {
  
  public void clientAction(HttpServletRequest req, AbstractController ctrl) {
    Class beanClazz = ctrl.getBean();
    Class remoteClazz = ctrl.getRemote();
    
    Object proxyObj = BeanHelper.getBean(beanClazz, remoteClazz);
    
    ctrl.setProxy(proxyObj);
  }
}
