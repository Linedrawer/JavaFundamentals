package org.zeroturnaround.jf.sysdump.test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.gson.Gson;
import org.zeroturnaround.jf.sysdump.Info;
import org.zeroturnaround.jf.sysdump.SystemDump;
import org.zeroturnaround.jf.sysdump.SystemDumpImpl;

public class SimpleJsonTest extends BaseTest {

  private static Path file;

  private static InfoImpl data;

  @BeforeClass
  public static void init() throws Exception {
    file = Files.createTempFile("sysdump", ".json");
    SystemDump dump = new SystemDumpImpl();
    dump.writeJson(SimpleInfoFactory.getInfo(), file);
    try (Reader reader = Files.newBufferedReader(file)) {
      data = new Gson().fromJson(reader, InfoImpl.class);
    }
  }

  @AfterClass
  public static void destroy() throws IOException {
    Files.deleteIfExists(file);
  }

  @Override
  protected Info getExpected() {
    return SimpleInfoFactory.getInfo();
  }

  @Override
  protected Info getActual() {
    return data;
  }

}
