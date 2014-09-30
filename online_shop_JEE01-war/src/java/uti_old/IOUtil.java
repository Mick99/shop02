/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author User
 */
public class IOUtil {
    public static void copy(InputStream is, OutputStream os) throws IOException {
    // Vom InputStream (is) zum OutputStream (os) kopieren:
    int read = 0;
    byte[] bytes = new byte[1024];
    while ((read = is.read(bytes)) != -1) {
      os.write(bytes, 0, read);
    }
  }
}
