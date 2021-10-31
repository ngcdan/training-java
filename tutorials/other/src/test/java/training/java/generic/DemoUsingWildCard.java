package training.java.generic;

import java.util.ArrayList;
import java.util.List;

public class DemoUsingWildCard {
  
  static class A {}
  static class B extends A {}
  static class C extends B {}
  static class D extends B {}
  
  List<? extends A> listA;
  List<? super B> listB;
  
  public DemoUsingWildCard() {
    listA = new ArrayList<B>();
    listA = new ArrayList<C>();
    listA = new ArrayList<D>();
    
    listB = new ArrayList<A>();
    //listB = new ArrayList<C>(); not compile
  }
  
  public void someMethod(List<? extends B> lb) {
    // chỉ lấy phần tử ra khỏi cấu trúc, nhưng không thể đưa phần tử vào cấu trúc
    B b = lb.get(0);
    //lb.add(new C()); //will not compile as we do not know the type of the list, only that it is bounded above by B
  }
  
  public void otherMethod(List<? super B> lb) {
    // đưa phần tử vào cấu trúc, nhưng không thể lấy phần từ ra khỏi cấu trúc
    //B b = lb.get(0); // will not compile as we do not know whether the list is of type B, it may be a List<A> and
    // only contain instances of A
    lb.add(new B());
  }
}

