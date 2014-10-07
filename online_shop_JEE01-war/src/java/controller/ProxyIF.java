/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Mick_02
 */
public interface ProxyIF {
  public Class getBean();
  public Class getRemote();
  public void setProxy(Object proxy);
}
