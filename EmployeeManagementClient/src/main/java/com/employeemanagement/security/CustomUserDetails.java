package com.employeemanagement.security;


import com.employeemanagement.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String password;
    private String username;
    private GrantedAuthority authority;

    public CustomUserDetails() {
    }

    public CustomUserDetails(String username, GrantedAuthority authority) {
        this.username = username;
        this.authority = authority;
    }

    public CustomUserDetails(UserDTO user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.authority = new SimpleGrantedAuthority(user.getUserRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
