/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Static 
 * @author Mick_02
 */
public class LoggerHelper {
  public static final Logger msgLog = LogManager.getFormatterLogger("MSG");
  public static final Logger excLog = LogManager.getFormatterLogger("EXC");
}
