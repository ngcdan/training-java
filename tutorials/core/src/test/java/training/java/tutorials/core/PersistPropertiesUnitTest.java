package training.java.tutorials.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class PersistPropertiesUnitTest {

  @Test
  public void persistPropsAsXML() {
    Properties props = new Properties();
    props.setProperty("name", "Le Ngoc Dan");

    try(OutputStream out = Files.newOutputStream(Paths.get("props.xml"))) {
      props.storeToXML(out, "My comment");
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void loadPropsFromXml() {
    Properties props = new Properties();
    try(InputStream in = Files.newInputStream(Paths.get("props.xml"))) {
      props.loadFromXML(in);
      String name = props.getProperty("name");
      System.out.println(name);
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}