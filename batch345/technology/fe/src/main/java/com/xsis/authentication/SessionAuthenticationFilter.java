package com.xsis.authentication;

import com.xsis.master.user.UserAuthentication;
import com.xsis.master.user.UserModel;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.EnumSet;

@Component
public class SessionAuthenticationFilter implements Filter {

    private static final EnumSet<AuthenticationRoles> AUTHENTICATION_ROLES = EnumSet.allOf(AuthenticationRoles.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("ROLE") != null) {
            AuthenticationRoles role = (AuthenticationRoles) session.getAttribute("ROLE");
            UserModel userModel = (UserModel) session.getAttribute("user");

            if (!AUTHENTICATION_ROLES.contains(role)) {
                session.invalidate();
                SecurityContextHolder.clearContext();
                response.sendRedirect("/403");
                return;
            }

            UserAuthentication userAuthentication = new UserAuthentication(userModel, role);
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        }

        filterChain.doFilter(request, response);
    }
}
