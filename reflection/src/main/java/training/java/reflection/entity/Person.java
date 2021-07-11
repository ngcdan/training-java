package training.java.reflection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.reflection.annotation.Column;
import training.java.reflection.annotation.PrimaryKey;

@Getter @Setter
@NoArgsConstructor
public class Person {
  
  @PrimaryKey
  private Long id;
  
  @Column
  private String name;
  
  @Column
  private int age;
  
  String getInfo() {
    return name + " " + age;
  }
  
  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  
  public static Person of(String name, int age) {
    return new Person(name, age);
  }
}
