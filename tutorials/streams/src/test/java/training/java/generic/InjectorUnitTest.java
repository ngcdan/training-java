package training.java.generic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

public class InjectorUnitTest {
  
  @Test
  public void injectorUnitTest() {
    Injector injector = new Injector().with("Hello World");
    Logger logger = injector.newInstance(Logger.class);
    logger.log();
  }
  
  public static class StringList extends ArrayList<String> {}
  
  @Test
  public void reifiableUnitTest() {
    System.out.println(int.class);
    
    System.out.println(String.class);
    
    List<?> wildcards = new ArrayList<>();
    System.out.println(wildcards.getClass());
    
    List raw = new ArrayList();
    System.out.println(raw.getClass());
    
    System.out.println(raw.getClass() == wildcards.getClass());
    
    System.out.println(int[].class);
    System.out.println(List[].class);
    
    List<String> strings = new ArrayList<>();
    Class<?> arrayListClass = strings.getClass();
    System.out.println(arrayListClass);
    
    TypeVariable<?>[] typeParameters = arrayListClass.getTypeParameters();
    System.out.println(Arrays.toString(typeParameters));
    
    System.out.println(StringList.class.toGenericString());
    ParameterizedType superclass = (ParameterizedType) StringList.class.getGenericSuperclass();
    Type typeVariable = superclass.getActualTypeArguments()[0];
    System.out.println(typeVariable);
    
  }
  
  @Test
  public void nonReifiableUnitTest() {
    // System.out.println(T.class);
    
    List<String> strings = new ArrayList<>();
    System.out.println(strings.getClass());
    List<Integer> integers = new ArrayList<>();
    List raw = new ArrayList();
    
    System.out.println(strings.getClass() == integers.getClass());
    System.out.println(raw.getClass() == integers.getClass());
    
    List<? extends Number> numbers = new ArrayList<>();
    System.out.println(numbers.getClass());
    System.out.println(numbers.getClass() == integers.getClass());
  }
}

class Injector {
  private Map<Class<?>, Object> objectGraph = new HashMap<>();
  
  public <T> T newInstance(Class<T> type) {
    return (T) objectGraph.computeIfAbsent(type, this::instantiate);
  }
  
  public Injector with(String value) {
    objectGraph.put(value.getClass(), value);
    return this;
  }
  
  private Object instantiate(Class<?> type) {
    try {
      Constructor<?>[] constructors = type.getConstructors();
      if (constructors.length != 1) {
        throw new IllegalArgumentException(type + " must only have 1 constructor");
      }
      
      Constructor<?> constructor = constructors[0];
      Object[] args = Stream.of(constructor.getParameterTypes())
        .map(param -> (Object) newInstance(param))
        .toArray();
      
      return constructor.newInstance(args);
    }
    catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
class Logger {
  private String value;
  
  public Logger(String value) {
    this.value = value;
  }
  
  public void log() {
    System.out.println(value);
  }
}
