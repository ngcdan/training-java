package training.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import training.java.reflection.entity.Person;
import training.java.reflection.util.ColumnField;
import training.java.reflection.util.MetaModel;
import training.java.reflection.util.PrimaryKeyField;

public class ReflectionUnitTest {

  @Test
  public void reflectionAPIUnitTest() throws Exception {
    // there is only one instance of Class for a given class
    String hello = "hello";
    Class<? extends String> helloClass = hello.getClass();

    Class<? extends String> worldClass = "World".getClass();
    Assertions.assertEquals(helloClass, worldClass);

    Class<String> stringClass = String.class;
    Assertions.assertEquals(helloClass, stringClass);

    String stringClassName = "java.lang.String";
    Class<?> stringClassForName = Class.forName(stringClassName);
    Assertions.assertEquals(helloClass, stringClassForName);

    // get  super class of Class (return only super class)
    Class<?> superclass = helloClass.getSuperclass();
    Assertions.assertEquals(Object.class, superclass);

    // super class of Object class
    Class<?> objectClass = Object.class;
    Assertions.assertNull(objectClass.getSuperclass());

    Class<Person> personClass = Person.class;
    //   Field name = personClass.getField("name");

    Field[] publicFields = personClass.getFields();
    Assertions.assertEquals(publicFields.length, 0);

    Field[] declaredFields = personClass.getDeclaredFields();
    Assertions.assertEquals(declaredFields.length, 3);

    for(Field field : declaredFields) {
      System.out.println(field);
    }

    Method[] methods = personClass.getMethods();
    for(Method method : methods) {
      System.out.println(method);
    }

    Field field = declaredFields[0];
    int modifiers = field.getModifiers();
    boolean isPublic = modifiers == 0x00000001;
    System.out.println(isPublic);
    Assertions.assertFalse(isPublic);

    System.out.println("Static Declared Method: ");
    Arrays.stream(methods).filter(method -> Modifier.isStatic(method.getModifiers())).forEach(System.out::println);

    // set value for a field
    Person person = new Person();
    Class<? extends Person> personClass1 = person.getClass();
    Field fieldName = personClass1.getDeclaredField("name");
    fieldName.setAccessible(true);
    fieldName.set(person, "Dan");

    System.out.println("Name : ");
    System.out.println(person.getName());
    Assertions.assertEquals("Dan", person.getName());
  }

  @Test
  public void metaModelUnitTest() throws Exception {
    MetaModel metaModel = MetaModel.of(Person.class);
    PrimaryKeyField primaryKeyField = metaModel.getPrimaryKey();
    List<ColumnField> columnFields = metaModel.getColumns();

    System.out
      .println("Primary Key: " + primaryKeyField.getName() + ", Type :" + primaryKeyField.getType().getSimpleName());

    for(ColumnField column : columnFields) {
      System.out.println("Column Name: " + column.getName() + ", Column Type :" + column.getType().getSimpleName());
    }
  }
}