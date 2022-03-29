package training.java.tutorials.core;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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

  @Test
  public void loadProps() {
    Properties props = new Properties();
    try (Reader reader = Files.newBufferedReader(Paths.get("src/myapp.properties"))) {
      props.load(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String val1 = props.getProperty("val1");
    String val2 = props.getProperty("val2");
    String val3 = props.getProperty("val3");
    String val4 = props.getProperty("val4");
    boolean x = val1.equals(val2);
    boolean y = val3.equals(val4);
    System.out.println(x);
    System.out.println(y);
  }
}