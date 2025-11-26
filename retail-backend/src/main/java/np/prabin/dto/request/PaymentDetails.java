package np.prabin.dto.request;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Embeddable
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class PaymentDetails {
//
//    private String razorpayOrderId;
//    private String razorpayPaymentId;
//    private String razorpaySignature;
//    private PaymentStatus status;
//
//    public enum PaymentStatus{
//        PENDING, COMPLETED, FAILED
//    }
//}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetails {

    private String sessionId;          // Stripe Checkout Session
    private String paymentIntentId;    // Stripe Payment Intent
    private String receiptUrl;         // Charge receipt url
    private PaymentStatus status;

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
}
