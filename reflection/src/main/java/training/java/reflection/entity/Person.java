package training.java.reflection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.reflection.annotation.Column;
import training.java.reflection.annotation.PrimaryKey;

@Getter
@Setter
@NoArgsConstructor
public class Person {
  
  @PrimaryKey(name = "test_id")
  private Long id;
  
  @Column(name = "test_name")
  private String name;
  
  @Column(name = "test_age")
  private int age;
  
  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  
  public static Person of(String name, int age) {
    return new Person(name, age);
  }
  
  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
  }
}
