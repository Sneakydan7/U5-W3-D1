package com.example.U5W3D1.security;

import com.example.U5W3D1.entities.Employee;
import com.example.U5W3D1.exceptions.UnauthorizedException;
import com.example.U5W3D1.services.EmployeeSRV;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private EmployeeSRV employeeSRV;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.startsWith("Bearer"))
            throw new UnauthorizedException("Please insert a valid token in header");


        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);


        String employeeId = jwtTools.extractIdFromToken(accessToken);

        Employee found = employeeSRV.getEmployeeById(UUID.fromString(employeeId));

        Authentication authentication = new UsernamePasswordAuthenticationToken(found, null, found.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
