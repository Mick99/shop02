/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Mick_02
 */
public class BeanHelper {
  public static final Logger msg = LogManager.getLogger("MSG");
  public static final Logger exc = LogManager.getLogger("EXC");

  private BeanHelper() {
  }
  public static Object getBean(final Class useBean, final Class useInterface) {
    Object bean = null;
    String jndi = buildJNDI(useBean, useInterface);
    msg.trace("jndi= " + jndi);
    try {
      Context context = new InitialContext();
      bean = context.lookup(jndi);
    } catch (NamingException ex) {
      exc.error("JNDI wrong: " + jndi, ex);
      throw new RuntimeException();
    }
    return bean;
  }
  private static String buildJNDI(final Class useBean, final Class useInterface) {
    StringBuilder jndi = new StringBuilder();
    jndi.append("java:global/online_shop_JEE01/online_shop_JEE01-ejb/");
    //TODO MW_141004: Full qualified Bean name throws NamingException, why??? May be, its <ejb-name> and not <ejb-class>
    jndi.append(useBean.getSimpleName());
    if (useInterface != null) {
      jndi.append("!");
      jndi.append(useInterface.getName());
    }
    return jndi.toString();
  }
}
