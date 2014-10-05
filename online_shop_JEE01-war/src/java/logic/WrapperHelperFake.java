/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import interfaces.BasketServiceRemote;
import service.BasketBean;

/**
 *
 * @author Mick_02
 */
public enum WrapperHelperFake {
  INSTANCE;
  
  private BasketServiceRemote bsr = (BasketServiceRemote) BeanHelper.getBean(BasketBean.class, BasketServiceRemote.class);
  
  public BasketServiceRemote getWrapper() {
    return bsr;
  }
}
