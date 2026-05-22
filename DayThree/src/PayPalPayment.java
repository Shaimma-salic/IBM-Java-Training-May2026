public class PayPalPayment extends Payment implements Verifiable {
    private String email;

    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment...");
    }

    @Override
    public boolean verify() {
        // Verification returns true only if email contains "@"
        return email != null && email.contains("@");
    }
}
