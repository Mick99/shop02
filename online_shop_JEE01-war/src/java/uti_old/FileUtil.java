/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.util.Arrays;


/**
 *
 * @author User
 */
public class FileUtil {

  public static String[] getFileList(String dirName) {
    File dir = new File(dirName); // C:\java\projekte\...
    String[] fileList = dir.list(); // downloadService.getFileList();
    System.out.println(Arrays.toString(fileList));
    return fileList;
  }
}
