package rishi.tadavarthi.Ecommerce_Clone.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RazorPayOrderResponse {
    private String id;
    private String entity;
    private Integer amount;
    private String status;
    private String  receipt;
    private Date created_at;
    private String currency;
}
