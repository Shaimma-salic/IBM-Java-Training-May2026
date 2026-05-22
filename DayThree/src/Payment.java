public abstract class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void processPayment();

    public void displayPaymentDetails() {
        System.out.println("Amount: " + amount);
    }
}
