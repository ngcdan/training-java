package training.java.object.orented.openclosedprincipal;

/*
public class Employee {
  private String name;
  
  public Employee(String name) { this.name = name;}
  public enum FORMAT { XML, JSON }
  public Employee(String source, FORMAT inputFormat) {}
  public Employee(Connection conn, int ID) {}
  public void exportAsJSON(Writer out) {}
  public void exportAsXML(Writer out) {}
  public void exportAsSQL(Connection out) {}
}
*/


public class Employee {
  private String name;
  
  public Employee(String name) { this.name = name;}
  
  interface Import {
    String fetchName();
  }
  
  interface Export {
    void storeName(String name);
  }
  
  public Employee(Import source) {
    name = source.fetchName();
  }
  
  public void export(Export destination) {
    destination.storeName(name);
  }
}

