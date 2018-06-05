package org.zeroturnaround.jf.sysdump;

import javafx.beans.property.Property;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Vladislav on 05/03/2017.
 */
public class InfoImpl implements Info{
    @Override
    public Map<String, String> getSystemEnvironment() {
        return new TreeMap<>(System.getenv());
    }

    @Override
    public Map<String, String> getSystemProperties() {
        TreeMap propertiesMap = new TreeMap<>(System.getProperties());

        return propertiesMap;
    }

    @Override
    public String getSystemVersion() throws InterruptedException, TimeoutException, IOException {

        if (System.getProperty("os.name").equals("Windows")){
            return  new ProcessExecutor().command("cmd", "ver")
                    .readOutput(true).execute()
                    .outputUTF8();
        } else {
            return  new ProcessExecutor().command("uname", "-a")
                    .readOutput(true).execute()
                    .outputUTF8();
        }

    }
}
