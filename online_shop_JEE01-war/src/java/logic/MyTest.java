/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import interfaces.UserServiceRemote;
import service.UserBean;

/**
 *
 * @author Mick_02
 */
public class MyTest {
	public static void main(String[] args) {
//		String n = "";
//		Long l = Long.parseLong(n);
//		System.out.println("l=" +l);
//    test(UserBean.class, UserServiceRemote.class);
    test(UserBean.class, null);
    
	}
  public static void test(Class c, Class i) {
    String s1 = c.getName();
    String s2 = c.getSimpleName();
    String s3 = c.getTypeName();
    if (i != null) {
    String i1 = i.getName();
    String i2 = i.getSimpleName();
    String i3 = i.getTypeName();
    }
    
    System.out.printf("Class: %s, %s, %s%n", s2, s1, s3);
//    System.out.printf("Inter: %s, %s, %s", i2, i1, i3);
  }
}
