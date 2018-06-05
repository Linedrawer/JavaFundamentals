Java Fundamentals - I/O 2 Homework
===========

Description
----------

Your task is to finish a Java program which outputs system info as XML and JSON files. Complete the implementation of `SystemDumpImpl` that implements the following interface:

```
public interface SystemDump {

  /**
   * @return current system dump info.
   */
  Info newInfo() throws Exception;

  /**
   * Write given data into a file as XML.
   */
  void writeXml(Info src, Path dest) throws Exception;

  /**
   * Write given data into a file as JSON.
   */
  void writeJson(Info src, Path dest) throws Exception;

}

public interface Info {

  /**
   * @return sorted map of all environment variables.
   */
  Map<String, String> getSystemEnvironment();

  /**
   * @return sorted map of all system properties.
   */
  Map<String, String> getSystemProperties();

  /**
   * @return output of a system-dependent command line tool giving some system info.
   *  On Windows: <code>cmd /c ver</code>
   *  On others: <code>uname -a</code>
   */
  String getSystemVersion();

}
```

The project has a set of unit tests. To get the maximum points all those tests must pass. Existing tests cannot be altered, but new ones can be added.

Requirements
----------

1. Both Maps have to be sorted A-Z by keys.
2. Execute a proper command line tool depending on the OS and gather its output.
3. Use XML and JSON APIs of your choice (don't write a String directly).
4. Buffer data for better performance.
5. Close all resources properly.
6. The program must work on Windows, Linux and macOS.

Example JSON
------------

```
{  
   "systemEnvironment":{  
      "HOME":"/Users/mati",
      "MAVEN_OPTS":"-Xmx1024M"
   },
   "systemProperties":{  
      "java.specification.name":"Java Platform API Specification",
      "path.separator":":"
   },
   "systemVersion":"Darwin Stalker.local 15.6.0 Darwin Kernel Version 15.6.0: Mon Aug 29 20:21:34 PDT 2016; root:xnu-3248.60.11~1/RELEASE_X86_64 x86_64\\n"
}
```

Example XML
-----------

```
<systemDump>
  <systemEnvironment>
    <entry key="HOME">/Users/mati</entry>
    <entry key="MAVEN_OPTS">-Xmx1024M</entry>
  </systemEnvironment>
  <systemProperties>
    <entry key="java.specification.name">Java Platform API Specification</entry>
    <entry key="path.separator">:</entry>
  </systemProperties>
  <systemVersion>
    Darwin Stalker.local 15.6.0 Darwin Kernel Version 15.6.0: Mon Aug 29 20:21:34 PDT 2016; root:xnu-3248.60.11~1/RELEASE_X86_64 x86_64\n
  </systemVersion>
</systemDump>
```

Various tips
-------------
1. You can use JAXB to output XML, but due to the way tests are built you then need to wrap the Info object passed to `SystemDump::writeXml` with your own JAXB annotated class and then
delegate all of the calls to get the data to the wrapped object. Otherwise you can get the following exception:

   ```javax.xml.bind.JAXBException: class org.zeroturnaround.jf.sysdump.test.InfoImpl nor any of its super class is known to this context.```
   
   This is actually a well known design pattern called Decorators. For more info on decorators you can read [this blog post](http://www.yegor256.com/2015/02/26/composable-decorators.html). You can apply the same principle to have a decorator with JAXB annotations.
2. If you want to build the distribution zip without fixing the tests, then you can do that by skipping tests in the build by adding -DskipTests to the command:

   ```shell
   ./mvnw clean deploy -DskipTests
   ```
   However, note that to get the full score for this assignment, you still need to make the tests pass correctly.

Submitting your assignment
--------------------------

For your convenience, we have set up the Maven project to ZIP up all files in your project folder so it is easy for you to attach it to an e-mail and send it our way. All you need to do is to execute the following command in your project folder:

```
./mvnw clean deploy
```

It will ask you for your full name, Student Book Number (also known as *matrikli number*) and a comment (optional).

Example:

```bash
./mvnw clean deploy

#...skipping building, testing and packaging output from Maven...

[INFO] --- maven-antrun-plugin:1.8:run (package homework ZIP) @ homework4 ---
[INFO] Executing tasks

main:
Your full name (e.g. John Smith):
Jane Smith
Your Student Book Number (matrikli number, e.g. B12345):
B12345
Comment:
Java IO
      [zip] Building zip: /Users/jane/workspace/jf-hw-sysdump/target/jf-homework4-B12345.zip
   [delete] Deleting: /Users/jane/workspace/jf-hw-sysdump/homework.properties
[INFO] Executed tasks
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 20.041 s
[INFO] Finished at: 2017-02-03T11:35:11+02:00
[INFO] Final Memory: 21M/283M
[INFO] ------------------------------------------------------------------------
```

After Maven has finished, you can find the generated ZIP file in *target* folder with name such as 
*jf-homework5-B12345.zip* (it contains your Student Book Number/matrikli number and homework number).

The only thing left to do now is to send the ZIP file as an attachment to an e-mail with subject **"Homework 5 - *your Student Code/maktrikli number*"** to *jf@zeroturnaround.com*.
