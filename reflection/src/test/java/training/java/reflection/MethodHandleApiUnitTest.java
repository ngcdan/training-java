package training.java.reflection;

import org.junit.jupiter.api.Test;
import training.java.reflection.entity.Person;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class MethodHandleApiUnitTest {
  
  @Test
  public void methodHandleApiUnitTest() throws Throwable {
    Lookup lookup = MethodHandles.lookup();
    
    // create instance of person using empty constructor
    MethodType emptyConstructorMethodType = MethodType.methodType(void.class);
    MethodHandle emptyConstructor = lookup.findConstructor(Person.class, emptyConstructorMethodType);
    Person p = (Person) emptyConstructor.invoke();
    System.out.println(p);
  
  
    MethodType constructorMethodType = MethodType.methodType(void.class, String.class, int.class);
    MethodHandle constructor = lookup.findConstructor(Person.class, constructorMethodType);
    Person p1 = (Person) constructor.invoke("Dan", 22);
    System.out.println(p1);
  
    MethodType nameGetterMethodType = MethodType.methodType(String.class);
    MethodHandle getName = lookup.findVirtual(Person.class, "getName", nameGetterMethodType);
    String name = (String) getName.invoke(p1);
    System.out.println(name);
  
    MethodType nameSetterMethodType = MethodType.methodType(void.class, String.class);
    MethodHandle setName = lookup.findVirtual(Person.class, "setName", nameSetterMethodType);
    setName.invoke(p1, "Test");
    System.out.println(p1);
  
    Lookup privateLookup = MethodHandles.privateLookupIn(Person.class, lookup);
    MethodHandle nameReader = privateLookup.findGetter(Person.class, "name", String.class);
    String name1 = (String) nameReader.invoke(p1);
    System.out.println(name1);
  }
}
