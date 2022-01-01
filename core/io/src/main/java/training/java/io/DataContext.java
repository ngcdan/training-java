package training.java.io;

import training.java.ds.map.MapObject;

public class DataContext {
  private final MapObject attributes = new MapObject();

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

  public String resolveVariables(String text, String ... variables) {
    for(String sel : variables) {
      Object value = attributes.get(sel);
      text = text.replace("{" + sel + "}", value.toString());
    }
    return text;
  }
}