package com.employeemanagement.security;

import com.employeemanagement.dto.UserDTO;
import com.employeemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Unable to found user: " + username);
        }

        return new CustomUserDetails(user);
    }
}