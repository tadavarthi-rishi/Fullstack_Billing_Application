package rishi.tadavarthi.Ecommerce_Clone.service;

import org.springframework.data.domain.Pageable;
import rishi.tadavarthi.Ecommerce_Clone.io.OrderRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.OrderResponse;
import rishi.tadavarthi.Ecommerce_Clone.io.PaymentVerificationRequest;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> getLatestOrders();

    OrderResponse verifyPayment(PaymentVerificationRequest request);

    Double sumSalesByDate(LocalDate date);

    Long countByOrderDate(LocalDate date);

    List<OrderResponse> findRecentOrders();
}
