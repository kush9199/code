package dev.learn.JWTAuth.controller.insecure;

import dev.learn.JWTAuth.dto.LoginReq;
import dev.learn.JWTAuth.entity.User;
import dev.learn.JWTAuth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginReq creds) {
        String token = authService.login(creds);
        return ResponseEntity.ok(token);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        authService.signup(user);
        return ResponseEntity.ok("signup success");
    }
}
