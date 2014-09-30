package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTools {
  public static void copy(InputStream is, OutputStream os) throws IOException {
    int read = 0;
    byte[] bytes = new byte[1024];
    while ((read = is.read(bytes)) != -1) {
      os.write(bytes, 0, read);
    }
  }
}
