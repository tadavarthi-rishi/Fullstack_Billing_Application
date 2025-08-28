package rishi.tadavarthi.Ecommerce_Clone.controller;

import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rishi.tadavarthi.Ecommerce_Clone.io.OrderResponse;
import rishi.tadavarthi.Ecommerce_Clone.io.PaymentRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.PaymentVerificationRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.RazorPayOrderResponse;
import rishi.tadavarthi.Ecommerce_Clone.service.OrderService;
import rishi.tadavarthi.Ecommerce_Clone.service.RazorpayService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RazorpayService razorpayService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public RazorPayOrderResponse createRazorpayOrder(@RequestBody PaymentRequest request) throws RazorpayException {
        return razorpayService.createOrder(request.getAmount(),request.getCurrency());
    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request) {
        return orderService.verifyPayment(request);
    }
}
