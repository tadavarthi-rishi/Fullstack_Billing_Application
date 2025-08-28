package rishi.tadavarthi.Ecommerce_Clone.service;

import com.razorpay.RazorpayException;
import rishi.tadavarthi.Ecommerce_Clone.io.RazorPayOrderResponse;

public interface RazorpayService {

   RazorPayOrderResponse createOrder(Double amount, String currency) throws RazorpayException;
}
