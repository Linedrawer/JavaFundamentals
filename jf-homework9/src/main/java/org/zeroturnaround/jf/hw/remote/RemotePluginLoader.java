package org.zeroturnaround.jf.hw.remote;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class RemotePluginLoader extends ClassLoader {
  private final File pluginLocation;

  public RemotePluginLoader(String pluginName) {
    this.pluginLocation = new File("plugins-remote", pluginName);
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    Class clazz;
    try {
      clazz = getParent().loadClass(name);
      return clazz;
    }
    catch (ClassNotFoundException e) {
    }

    URL url = null;
    try {
      url = new URL("https://raw.githubusercontent.com/JavaFundamentalsZT/jf-hw-classloaders/master/plugins-remote/"
          + name.substring(name.lastIndexOf(".") + 1) + "/"
          + name.substring(name.lastIndexOf(".") + 1) + ".png");
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }

    byte[] picData;
    try {
      picData = DatatypeConverter.parseHexBinary(cutString(url));
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }

    return defineClass(name, picData, 0, picData.length);
  }

  public static String cutString(URL imageURL) throws IOException {
    return getFullHex(imageURL).substring(getFullHex(imageURL).indexOf("CAFEBABE"));
  }

  public static String getFullHex(URL imageURL) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try (InputStream in = imageURL.openStream()) {
      byte[] data = new byte[256];
      for (int i; (i = in.read(data)) > 0;) {
        out.write(data, 0, i);
      }
    }
    out.close();
    return DatatypeConverter.printHexBinary(out.toByteArray());
  }
}
