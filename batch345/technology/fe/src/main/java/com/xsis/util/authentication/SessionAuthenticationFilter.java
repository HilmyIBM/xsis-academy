package com.xsis.util.authentication;

import com.xsis.master.auth.AuthRole;
import com.xsis.master.customer.CustomerModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SessionAuthenticationFilter extends OncePerRequestFilter {

    private static final Set<AuthRole> VALID_ROLES =
            new HashSet<>(Arrays.asList(AuthRole.ADMIN, AuthRole.USER));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("ROLE") == null) {
            SecurityContextHolder.clearContext();
            response.sendRedirect("/401");
            return;
        }

        AuthRole role = (AuthRole) session.getAttribute("ROLE");

        if (!VALID_ROLES.contains(role)) {
            SecurityContextHolder.clearContext();
            response.sendRedirect("/403");
            return;
        }

        CustomerModel customerData = (CustomerModel) session.getAttribute("user");
        Authentication authentication = new UserAuthentication(customerData.getEmail(), role);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
