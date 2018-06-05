package org.zeroturnaround.jf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.zeroturnaround.jf.sysdump.Info;
import org.zeroturnaround.jf.sysdump.InfoImpl;
import org.zeroturnaround.jf.sysdump.SystemDump;
import org.zeroturnaround.jf.sysdump.SystemDumpImpl;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        SystemDump dump = new SystemDumpImpl();
        log.info("Creating info");
        org.zeroturnaround.jf.sysdump.Info info = dump.newInfo();
        Path xml = Files.createTempFile("sysdump", ".xml");
        Path json = Files.createTempFile("sysdump", ".json");
        log.info("Writing into {}", json);
        dump.writeJson(info, json);
        log.info("Writing into {}", xml);
        dump.writeXml(info, xml);
        log.info("Finished");
//        Info infoz = new InfoImpl();
//        Map<String, String> map = infoz.getSystemEnvironment();
//        map.forEach((k,v) -> System.out.println("Key " + k + " Value " + v));
//        System.out.println("\n\n");
//        Map<String,String> mmap = infoz.getSystemProperties();
//        mmap.forEach((k,v) -> System.out.println("Key " + k + " Value " + v));


    }

}
