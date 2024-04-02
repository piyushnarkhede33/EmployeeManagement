package com.employeemanagement.service.impl;

import com.employeemanagement.dto.UserDTO;
import com.employeemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public UserDTO getByUsername(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("content-type", "application/json"));
        ResponseEntity<UserDTO> userResponse =
                restTemplate.exchange(apiUrl + "/users/getUserDetails?userName=" + username, HttpMethod.GET,
                        requestEntity, new ParameterizedTypeReference<UserDTO>() {
        });
        return userResponse.getBody();
    }
}
