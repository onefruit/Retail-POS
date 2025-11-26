package np.prabin.service;

import com.stripe.exception.StripeException;
import np.prabin.dto.response.PaymentResponse;
import np.prabin.model.Order;

public interface PaymentService {

    PaymentResponse createPaymentLink(Order order) throws StripeException;
}
