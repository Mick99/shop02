/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Mick_02
 */
public class BeanHelper {

  private BeanHelper() {
  }
	public static Object getBean(String jndiName) {
		Object bean = null;
		try {
			Context context = new InitialContext();
			bean = context.lookup(jndiName);
		} catch (NamingException ex) {
			Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("JNDI wrong: " + jndiName);
		}
		return bean;
	}
}
