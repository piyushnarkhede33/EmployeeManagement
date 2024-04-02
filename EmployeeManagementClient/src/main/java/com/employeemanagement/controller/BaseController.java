package com.employeemanagement.controller;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.dto.UserDTO;
import com.employeemanagement.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.url}")
    String apiUrl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    RedirectView indexRedirect(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken authentication) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equalsIgnoreCase("ADMIN"))) {
                return new RedirectView("/users");
            } else if (userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equalsIgnoreCase("USER"))) {
                return new RedirectView("/employees");
            }
        }
        return new RedirectView("/employees");
    }


    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('USER')")
    String serveEmployeesPage(Model model, Principal principal) {
        model.addAttribute("username",principal.getName());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("content-type", "application/json"));
        ResponseEntity<List<EmployeeDTO>> employees = restTemplate.exchange(apiUrl + "/employees", HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<EmployeeDTO>>() {
        });

        model.addAttribute("employees", employees.getBody());
        return "employees";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    String serveUsersPage(Model model, Principal principal) {

        model.addAttribute("username",principal.getName());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("content-type", "application/json"));
        ResponseEntity<List<UserDTO>> users = restTemplate.exchange(apiUrl + "/users?role=USER", HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<UserDTO>>() {
        });

        model.addAttribute("users", users.getBody());

        return "users";
    }


    @GetMapping("/employees/{id}")
    @PreAuthorize("hasAuthority('USER')")
    String serveEmpDetailsPage(Model model, @PathVariable Integer id, Principal principal) {

        model.addAttribute("username",principal.getName());

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Map.of("content-type", "application/json"));
        ResponseEntity<EmployeeDTO> employee = restTemplate.exchange(apiUrl + "/employees/" + id, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<EmployeeDTO>() {
        });

        model.addAttribute("employee", employee.getBody());

        return "employee";
    }


    @GetMapping("add-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    String addUser() {
        return "add-user";
    }


    @PostMapping("add-user")
    String addUser(@RequestParam("userName") String username, @RequestParam("password") String password,
                   @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                   @RequestParam("emailid") String emailId, @RequestParam("phoneNo") String phoneNumber) {

        String encodedPassword = passwordEncoder.encode(password);
        UserDTO userDTO = new UserDTO(null, username, encodedPassword, emailId, phoneNumber, "USER", firstName, lastName);
        UserDTO user = restTemplate.postForObject(apiUrl + "/users", userDTO, UserDTO.class);
        return "add-user";
    }
}
