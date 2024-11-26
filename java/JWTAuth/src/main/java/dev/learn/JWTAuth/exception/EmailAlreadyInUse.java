package dev.learn.JWTAuth.exception;

public class EmailAlreadyInUse extends RuntimeException {
    public EmailAlreadyInUse() {
        super("Email already in use");
    }
}
