// Define sealed class hierarchy for payment types
public abstract sealed class PaymentType permits PaymentType.OnlinePaymentType, PaymentType.OfflinePaymentType {

    // Represents online payment categories (Credit Card, PayPal)
    public static final class OnlinePaymentType extends PaymentType {
    }

    // Represents offline payment categories (Bank Transfer)
    public static final class OfflinePaymentType extends PaymentType {
    }
}

