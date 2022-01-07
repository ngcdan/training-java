package training.java.xlsx;

import lombok.Getter;
import training.java.ds.map.MapObject;
import training.java.io.DataContext;

import java.util.ArrayList;
import java.util.List;

public class SectionContext {
  @Getter
  private String name;

  @Getter
  private HeaderMapping headerMapping;

  @Getter
  private DataContext dataContext;

  private final MapObject attributes = new MapObject();

  @Getter
  private List<String> errors;

  public SectionContext() {}

  public SectionContext(String name, DataContext ctx, HeaderMapping headerMapping) {
    this.name = name;
    this.dataContext = ctx;
    this.headerMapping = headerMapping;
  }

  public <T> T getAttribute(String name) {
    return (T) attributes.get(name);
  }

  public <T> T getAttribute(Class<T> clazz) {
    return (T) attributes.get(clazz.getName());
  }

  public <T> void addAttribute(T object) {
    attributes.put(object.getClass().getName(), object);
  }

  public <T> void addAttribute(String name, T object) {
    attributes.put(name, object);
  }

  public boolean hasError() {
    return errors != null && errors.size() > 0;
  }

  public void addError(String name, String message) {
    if(errors == null) errors = new ArrayList<>();
    this.errors.add("[" + name + "] " + message);
  }

  public void dumpError() {
    if(errors == null) return;
    System.out.println("SectionContext " + name + " has errors:");
    System.out.println("--------------------------------------------------------");
    for(String error : errors) {
      System.out.println(error);
    }
    System.out.println("--------------------------------------------------------");
  }
}