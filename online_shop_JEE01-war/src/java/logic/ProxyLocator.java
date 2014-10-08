/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import persistence.User;

/**
 *
 * @author mwitt
 */
public enum ProxyLocator {
  INTSTANCE;

  private static final Map<SessionId, StuffHolder> prx = new HashMap<>();;
  
  private ProxyLocator() {
  }
//  public setSessionId
  
}

class StuffHolder {
  private User user;
  private Proxy proxy;
  
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Proxy getProxy() {
    return proxy;
  }
  public void setProxy(Proxy proxy) {
    this.proxy = proxy;
  }
}

class Proxy {
  private List<ProxyUsedByController> pcs;

  Proxy(){
    
  }
  class ProxyUsedByController {
    private Class controller;
    private Object proxy;

  }

}

class SessionId {
  private String httpSessionId;

  public String getHttpSessionId() {
    return httpSessionId;
  }
  public void setHttpSessionId(String httpSessionId) {
    this.httpSessionId = httpSessionId;
  }
  @Override
  public int hashCode() {
    int hash = 5;
    hash = 59 * hash + Objects.hashCode(this.httpSessionId);
    return hash;
  }
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final SessionId other = (SessionId) obj;
    if (!Objects.equals(this.httpSessionId, other.httpSessionId)) {
      return false;
    }
    return true;
  }
}
