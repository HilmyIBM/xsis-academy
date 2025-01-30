package com.xsis.master.user;

import com.xsis.authentication.AuthenticationRoles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class UserAuthentication implements Authentication {
    private final UserModel userModel;
    private final AuthenticationRoles role;
    private final boolean authenticated;

    private final EnumSet<AuthenticationRoles> roles = EnumSet.allOf(AuthenticationRoles.class);

    public UserAuthentication(UserModel userModel, AuthenticationRoles role) {
        this.userModel = userModel;
        this.role = role;
        this.authenticated = isValidRole(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (this.role) {
            case ROLE_ADMIN -> authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case ROLE_USER -> authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            default -> authorities.add(new SimpleGrantedAuthority("ROLE_GOBLIN"));
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
        return userModel.getEmail();
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
        return userModel.getEmail();
    }

    private boolean isValidRole(AuthenticationRoles role) {
        return roles.contains(role);
    }
}
