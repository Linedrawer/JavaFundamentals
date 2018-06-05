package org.zeroturnaround.jf.sysdump.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.zeroturnaround.jf.sysdump.Info;
import org.zeroturnaround.jf.sysdump.SystemDump;
import org.zeroturnaround.jf.sysdump.SystemDumpImpl;

public class RealXmlTest extends BaseTest {

  private static Path file;

  private static Info data;

  @BeforeClass
  public static void init() throws Exception {
    file = Files.createTempFile("sysdump", ".xml");
    SystemDump dump = new SystemDumpImpl();
    Info info = RealInfoFactory.getInfo();
    // Remove the value with should be escaped
    info.getSystemEnvironment().remove("PROMPT");
    dump.writeXml(info, file);
    data = XmlParser.parse(file);
  }

  @AfterClass
  public static void destroy() throws IOException {
    Files.deleteIfExists(file);
  }

  @Override
  protected Info getExpected() {
    return RealInfoFactory.getInfo();
  }

  @Override
  protected Info getActual() {
    return data;
  }

}
