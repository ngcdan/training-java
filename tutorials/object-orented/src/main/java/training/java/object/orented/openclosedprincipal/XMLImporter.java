package training.java.object.orented.openclosedprincipal;

import java.io.Reader;

public class XMLImporter implements Employee.Import {
  private Reader in;
  
  public XMLImporter(Reader in) {this.in = in;}
  
  @Override
  public String fetchName() {
    return "From XMLImporter";
  }
}



