package np.prabin.service.impl;

import np.prabin.dto.request.OrderRequest;
import np.prabin.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    void deleteOrder(String orderId);
    List<OrderResponse> getOrders();
}
