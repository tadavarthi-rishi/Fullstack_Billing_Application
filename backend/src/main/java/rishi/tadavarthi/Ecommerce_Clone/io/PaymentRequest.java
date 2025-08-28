package rishi.tadavarthi.Ecommerce_Clone.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Double amount;
    private String currency;
}
