package rishi.tadavarthi.Ecommerce_Clone.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String email;
    private String token;
    private String role;
}
