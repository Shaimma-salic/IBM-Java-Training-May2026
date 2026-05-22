import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Demonstrate polymorphism with collection of Payment objects
        List<Payment> payments = new ArrayList<>();
        payments.add(new CreditCardPayment(99.0, "1234567890123456"));
        payments.add(new PayPalPayment(25.0, "user@email.com"));
        payments.add(new BankTransferPayment(250.0, "1234567890"));

        // Create PaymentType instances to categorize each payment
        PaymentType.OnlinePaymentType onlineType = new PaymentType.OnlinePaymentType();
        PaymentType.OfflinePaymentType offlineType = new PaymentType.OfflinePaymentType();

        // Create gateway instance - using abstraction during instantiation
        Gateway gateway = new PaymentGateway();
        List<PaymentDetails> records = new ArrayList<>();

        // Process each payment
        for (Payment p : payments) {
            if (p instanceof Verifiable v) {
                if (v.verify()) {
                    ((PaymentGateway) gateway).processPayment(p);
                    records.add(new PaymentDetails(
                        UUID.randomUUID().toString(),
                        p.getAmount(),
                        p.getClass().getSimpleName(),
                        LocalDateTime.now().toString()
                    ));
                } else {
                    System.out.println("Verification failed.");
                }
            }
        }

        // Display all PaymentDetails records
        System.out.println("\n=== Payment Details Records ===");
        records.forEach(System.out::println);
    }
}   