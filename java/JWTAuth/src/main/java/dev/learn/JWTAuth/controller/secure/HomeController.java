package dev.learn.JWTAuth.controller.secure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/resources/")
public class HomeController {
    @GetMapping("get")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello World");
    }
}

