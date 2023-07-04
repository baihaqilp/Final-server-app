package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.dto.request.LoginRequest;
import id.co.metrodata.serverApp.models.dto.response.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.UserRequest;
import id.co.metrodata.serverApp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody UserRequest userRequest) {
        {
            return authService.register(userRequest);
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
