package rishi.tadavarthi.Ecommerce_Clone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rishi.tadavarthi.Ecommerce_Clone.io.UserRequest;
import rishi.tadavarthi.Ecommerce_Clone.io.UserResponse;
import rishi.tadavarthi.Ecommerce_Clone.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        try{
            return userService.createUser(userRequest);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to register user"+e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<UserResponse> readUsers() {
        return userService.readUsers();
}

@DeleteMapping("/users/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void deleteUser(@PathVariable String id){
     try {
         userService.deleteUser(id);
     } catch (Exception e){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"+e.getMessage());
     }
}
}
