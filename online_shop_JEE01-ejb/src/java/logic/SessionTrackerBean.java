/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static util.LoggerHelper.*;

/**
 * !! Brauch noch den Controller !!
 * 
 * @author Mick_02
 */
@Singleton
@Startup
@LocalBean
public class SessionTrackerBean {
  // Map<SessionId, Username> and later User
  private Map<String,ControllerToUserLink> tracker = new HashMap<>();
  
  public Boolean isNewSession(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String sessionId = session.getId();
    msgLog.trace("HttpSession getId: %s", sessionId);
    return true;
//    return isSessionExist(sessionId);
    
    // May be later usuful
//    Date createTime = new Date(session.getCreationTime());
//    Date lastAccessTime = new Date(session.getLastAccessedTime());
    
  }
  private boolean isSessionExist(String sessionId) {
    if (sessionId == null)
      throw new NullPointerException("HttpSession getId() is null??");
    if (!tracker.containsKey(sessionId)) {
      tracker.put(sessionId, new ControllerToUserLink());
      return false;
    } 
    return true;
  }
}
