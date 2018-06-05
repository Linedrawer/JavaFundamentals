package org.zeroturnaround.jf.hw.remote;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.zeroturnaround.jf.hw.Plugin;

public class RemotePluginManager {

  public static String[] findAllPlugins() {
    return findAllPluginInfos().keySet().toArray(new String[] {});
  }

  private static Map<String, String> findAllPluginInfos() {
    return new HashMap() {
      {
        put("NomNomNomPlugin", "https://raw.github.com/zeroturnaround/jf-hw-classloaders/master/plugins-remote/NomNomNomPlugin/README.properties");
        put("ChickenPlugin", "https://raw.github.com/zeroturnaround/jf-hw-classloaders/master/plugins-remote/ChickenPlugin/README.properties");
        put("HeadAndShouldersPlugin", "https://raw.github.com/zeroturnaround/jf-hw-classloaders/master/plugins-remote/HeadAndShouldersPlugin/README.properties");
      }
    };
  }

  public static Plugin getPluginInstance(String string) {
    // see the LocalPluginManager getPluginInstance(String str);
    // method and implement something similar.
    Map<String, String> allPlugins = findAllPluginInfos();
    String className;

    try {
      className = loadPropertiesFile(new URL(allPlugins.get(string))).getProperty("class");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException();
    }

    ClassLoader cl = new RemotePluginLoader(string);
    Class clazz;

    try {
      //
      clazz = cl.loadClass(className);
    }
    catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    try {
      return (Plugin) clazz.newInstance();
    }
    catch (InstantiationException e) {
      throw new RuntimeException(e);
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  /*
   * Simple method to load remote property files.
   */
  public static Properties loadPropertiesFile(URL url) {
    Properties tmp = new Properties();
    try (InputStream is = url.openStream()) {
      tmp.load(is);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return tmp;
  }
}
