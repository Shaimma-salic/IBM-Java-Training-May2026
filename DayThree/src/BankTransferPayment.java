
public class BankTransferPayment extends Payment implements Verifiable {
    private String accountNumber;

    public BankTransferPayment(double amount, String accountNumber) {
        super(amount);
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing bank transfer...");
    }

    @Override
    public boolean verify() {
        // Verification returns true only if account number length is 10
        return accountNumber != null && accountNumber.length() == 10;
    }
}
