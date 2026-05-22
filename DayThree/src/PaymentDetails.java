public record PaymentDetails(String transactionID, double amount, String paymentMethod, String timeStamp) {
    // Immutable data structure for payment transactions
}