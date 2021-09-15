package training.java.object.orented.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Customer {
  private final String name;
  private Map<String, PaymentMethod> paymentMethods = new HashMap<>();
  
  public void addPaymentMethod(String nickname, PaymentMethod paymentMethod) {
    paymentMethods.put(nickname, paymentMethod);
  }
  
  public Customer(String name) {
    this.name = name;
  }
  
  public int calculateDiscount() { return 0;}
  
  public Optional<Order> checkout(ShoppingCart cart, String payMethodNickname) {
    Optional<PaymentMethod> paymentMethod = Optional.ofNullable(paymentMethods.get(payMethodNickname));
    Optional<Payment> payment = paymentMethod.flatMap(pm -> pm.mkPayment(cart.getTotalCost()));
    return payment.map(value -> new Order(this, cart, value));
  }
  
  @Override
  public String toString() {
    return "Customer{" +
      "\nname = " + name + "}";
  }
}
