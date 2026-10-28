package fr.formation.config;

import java.io.IOException;

import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomDemoFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Ici on fait ce qu'on doit faire
        System.out.println("Coucou from filter");

        System.out.println(request.getHeader("Authorization"));

        // Chaque filtre envoi la suite au filtre suivant
        filterChain.doFilter(request, response);
    }
}
