package rishi.tadavarthi.Ecommerce_Clone.service;

import rishi.tadavarthi.Ecommerce_Clone.io.UserRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    String getUserRole(String email);
    List<UserResponse> readUsers();
    void deleteUser(String id);
}
