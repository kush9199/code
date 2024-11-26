package dev.learn.JWTAuth.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists");
    }
}
