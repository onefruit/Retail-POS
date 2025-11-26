package np.prabin.controller;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.OrderRequest;
import np.prabin.dto.response.OrderResponse;
import np.prabin.dto.response.PaymentResponse;
import np.prabin.model.Order;
import np.prabin.model.PaymentMethod;
import np.prabin.service.PaymentService;
import np.prabin.service.impl.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest request) throws StripeException {
        OrderResponse response = orderService.createOrder(request);
        PaymentResponse paymentLink = paymentService.createPaymentLink(convertToEntity(request));
        return new ResponseEntity<>(paymentLink, HttpStatus.CREATED);
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    private Order convertToEntity(OrderRequest request) {
        return Order.builder()
                .customerName(request.getCustomerName())
                .phoneNumber(request.getPhoneNumber())
                .subtotal(request.getSubtotal())
                .tax(request.getTax())
                .grandTotal(request.getGrandTotal())
                .paymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
