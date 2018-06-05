package org.zeroturnaround.jf.sysdump.test;

import org.junit.BeforeClass;

import org.zeroturnaround.jf.sysdump.Info;
import org.zeroturnaround.jf.sysdump.SystemDumpImpl;

public class NewInfoTest extends BaseTest {

  private static Info data;

  @BeforeClass
  public static void init() throws Exception {
    data = new SystemDumpImpl().newInfo();
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
