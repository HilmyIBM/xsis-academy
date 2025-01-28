package com.xsis.util.authentication;

import com.xsis.master.auth.AuthRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class UserAuthentication implements Authentication {
    private final String email;
    private final AuthRole role;
    private final boolean authenticated;

    private final EnumSet<AuthRole> roles = EnumSet.allOf(AuthRole.class);

    public UserAuthentication(String email, AuthRole role) {
        this.email = email;
        this.role = role;
        this.authenticated = isValidRole(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (this.role) {
            case ADMIN -> authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case USER -> authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return email;
    }

    private boolean isValidRole(AuthRole role) {
        return roles.contains(role);
    }
}
