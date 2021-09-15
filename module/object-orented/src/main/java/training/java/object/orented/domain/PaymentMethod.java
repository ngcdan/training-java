package training.java.object.orented.domain;

import java.util.Optional;

public interface PaymentMethod {
  Optional<Payment> mkPayment(double value);
}
