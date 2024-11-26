package dev.learn.JWTAuth.service;

import dev.learn.JWTAuth.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JWTService {

    @Value("${JWTAuth.app.secret}")
    private String secret;
    @Value("${JWTAuth.app.expirationMS}")
    private int expiry;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(Authentication auth) {
        User u = (User) auth.getPrincipal();
        System.out.println(auth.getPrincipal().toString());
        return Jwts.builder()
                .subject(u.getUsername())
                .claim("authorities", u.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiry))
                .signWith(key())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key()).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public String getRolesFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key()).build()
                .parseSignedClaims(token)
                .getPayload().get("authorities").toString();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }

}
