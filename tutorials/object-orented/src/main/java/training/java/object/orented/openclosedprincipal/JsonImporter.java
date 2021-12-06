package training.java.object.orented.openclosedprincipal;

import java.io.Reader;

public class JsonImporter implements Employee.Import {

  private Reader in;

  public JsonImporter(Reader in) {this.in = in;}

  @Override
  public String fetchName() {
    return "From JsonImporter";
  }
}