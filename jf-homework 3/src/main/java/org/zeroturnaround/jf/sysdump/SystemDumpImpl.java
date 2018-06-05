package org.zeroturnaround.jf.sysdump;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class SystemDumpImpl implements SystemDump {

  private static final Logger log = LoggerFactory.getLogger(SystemDumpImpl.class);

  @Override
  public Info newInfo() throws Exception {
    // TODO
    return new InfoImpl();
  }

  @Override
  public void writeXml(Info src, Path dest) throws Exception {
    XMLOutputFactory output = XMLOutputFactory.newInstance();
    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(dest))) {
      XMLStreamWriter writer = output.createXMLStreamWriter(out);
      writer.writeStartDocument();
      writer.writeStartElement("systemDump");
      writer.writeStartElement("systemEnvironment");
      for (Map.Entry<String, String> entry : src.getSystemEnvironment().entrySet()) {
        writer.writeStartElement("entry");
        writer.writeAttribute("key", entry.getKey());
        writer.writeCharacters(entry.getValue());
        writer.writeEndElement();
      }
      writer.writeEndElement();
      writer.writeStartElement("systemProperties");
      for (Map.Entry<String, String> entry : src.getSystemProperties().entrySet()) {
        writer.writeStartElement("entry");
        writer.writeAttribute("key", entry.getKey());
        writer.writeCharacters(entry.getValue());
        writer.writeEndElement();
      }
      writer.writeEndElement();
      writer.writeStartElement("systemVersion");
      writer.writeCharacters(src.getSystemVersion());
      writer.writeEndElement();
      writer.writeEndDocument();
      writer.close();
    }



  }

  @Override
  public void writeJson(Info src, Path dest) throws Exception {
    // TODO
    try (Writer writer = Files.newBufferedWriter(dest)) {
      new Gson().toJson(src, writer);
    }
  }


}
