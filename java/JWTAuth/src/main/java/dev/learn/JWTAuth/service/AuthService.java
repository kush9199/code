package dev.learn.JWTAuth.service;

import dev.learn.JWTAuth.dto.LoginReq;
import dev.learn.JWTAuth.entity.User;
import dev.learn.JWTAuth.exception.EmailAlreadyInUse;
import dev.learn.JWTAuth.exception.UserAlreadyExists;
import dev.learn.JWTAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;

    public String signup(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExists();
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyInUse();
        }
        User u =
                User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        userRepository.save(u);
        return "success";
    }

    public String login(LoginReq creds) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtService.generateToken(auth);
    }
}
