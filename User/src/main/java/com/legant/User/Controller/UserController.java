package com.legant.User.Controller;

import com.legant.User.DTO.UserDTO;
import com.legant.User.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDTO userDTO) {
        return userAuthService.signUp(userDTO);
    }

    @GetMapping("/home")
    public String home() {
        return "Hello Home";
    }

    @GetMapping("product")
    private UserDTO getUserById(@RequestParam Long userId) {
        return userAuthService.getUserById(userId);
    }

}
