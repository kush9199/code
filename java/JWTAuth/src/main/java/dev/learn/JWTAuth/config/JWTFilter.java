package dev.learn.JWTAuth.config;

import dev.learn.JWTAuth.entity.User;
import dev.learn.JWTAuth.service.JWTService;
import dev.learn.JWTAuth.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJWT(request);
            if(jwt != null && jwtService.validateJwtToken(jwt)){
                String username = jwtService.getUsernameFromToken(jwt);
                System.out.println( "username is " + username);
                UserDetails u = userService.loadUserByUsername(username);
                System.out.println(u);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    private String parseJWT(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
