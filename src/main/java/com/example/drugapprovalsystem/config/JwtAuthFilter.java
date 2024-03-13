package com.example.drugapprovalsystem.config;

import com.example.drugapprovalsystem.exception.InvalidateException;
import com.example.drugapprovalsystem.exception.UserDoesNotExistException;
import com.example.drugapprovalsystem.service.ServiceInterface.JwtService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    private static final String HEADER = "Authorization";
    private static final String SUB_STRING = "Bearer ";
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER);
        String jwt;
        String userEmail;

        if (authHeader == null || !authHeader.startsWith(SUB_STRING)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(SUB_STRING.length());
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = null;
            try {
                user = userService.getUserByEmail(userEmail);
            } catch (UserDoesNotExistException | InvalidateException e) {
                throw new RuntimeException(e);
            }
            if (jwtService.isValidToken(jwt, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
