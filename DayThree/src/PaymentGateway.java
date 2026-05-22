public non-sealed class PaymentGateway extends Gateway {
    // Handles payment processing through gateway
    // Can be freely extended by specific implementations
    
    public void processPayment(Payment payment) {
        if (payment instanceof Verifiable v) {
            if (v.verify()) {
                payment.processPayment();
                System.out.println("Payment has been processed");
            }
        }
    }
}