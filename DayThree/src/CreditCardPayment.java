
public class CreditCardPayment extends Payment implements Verifiable {
    private String cardNumber;

    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment...");
    }

    @Override
    public boolean verify() {
        // Verification returns true only if card number length is 16
        return cardNumber != null && cardNumber.length() == 16;
    }
}