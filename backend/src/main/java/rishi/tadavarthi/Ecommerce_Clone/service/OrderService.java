package rishi.tadavarthi.Ecommerce_Clone.service;

import rishi.tadavarthi.Ecommerce_Clone.io.OrderRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> getLatestOrders();
}
