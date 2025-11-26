package np.prabin.service.impl;

import lombok.RequiredArgsConstructor;
import np.prabin.dto.request.OrderRequest;
import np.prabin.dto.request.PaymentDetails;
import np.prabin.dto.response.OrderResponse;
import np.prabin.model.Order;
import np.prabin.model.OrderItem;
import np.prabin.model.PaymentMethod;
import np.prabin.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = convertToEntity(request);

        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setStatus(order.getPaymentMethod() ==
                PaymentMethod.CASH ? PaymentDetails.PaymentStatus.COMPLETED : PaymentDetails.PaymentStatus.PENDING);
        order.setPaymentDetails(paymentDetails);

        List<OrderItem> items = request.getCartItems().stream().map(this::convertToOrderItemEntity)
                .collect(Collectors.toList());
        order.setItems(items);

        Order savedOrder = orderRepository.save(order);
        return convertToResponse(savedOrder);
    }

    private OrderItem convertToOrderItemEntity(OrderRequest.OrderItemRequest orderItemRequest) {
        return OrderItem.builder()
                .orderItemId(orderItemRequest.getOrderItemId())
                .name(orderItemRequest.getName())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .build();
    }

    private OrderResponse convertToResponse(Order savedOrder) {
        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .customerName(savedOrder.getCustomerName())
                .phoneNumber(savedOrder.getPhoneNumber())
                .subtotal(savedOrder.getSubtotal())
                .tax(savedOrder.getTax())
                .grandTotal(savedOrder.getGrandTotal())
                .paymentMethod(savedOrder.getPaymentMethod())
                .items(savedOrder.getItems().stream()
                        .map(this::convertToOrderItemResponse).collect(Collectors.toList()))
                .paymentDetails(savedOrder.getPaymentDetails())
                .createdAt(savedOrder.getCreatedAt())
                .build();
    }

    private OrderResponse.OrderItemResponse convertToOrderItemResponse(OrderItem orderItem) {
        return OrderResponse.OrderItemResponse.builder()
                .orderItemId(orderItem.getOrderItemId())
                .name(orderItem.getName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
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

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.delete(orderRepository.
                findByOrderId(orderId).orElseThrow(() -> new RuntimeException("order id not found")));
    }

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(this::convertToResponse).collect(Collectors.toList());

    }
}
