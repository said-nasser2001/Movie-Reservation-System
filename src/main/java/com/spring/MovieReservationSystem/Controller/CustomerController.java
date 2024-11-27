package com.spring.MovieReservationSystem.Controller;

import com.spring.MovieReservationSystem.Repository.AuthorityRepository;
import com.spring.MovieReservationSystem.Repository.CustomerRepository;
import com.spring.MovieReservationSystem.entity.Authority;
import com.spring.MovieReservationSystem.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            String hashPwd = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                Authority authority;
                if(savedCustomer.getRole().equals("write"))
                {
                     authority= new Authority("ROLE_ADMIN",savedCustomer);
                }
                else {
                     authority= new Authority("ROLE_USER",savedCustomer);
                }
                authorityRepository.save(authority);

                return ResponseEntity.status(HttpStatus.CREATED).
                        body("Given user details are successfully registered");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("User registration failed");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An exception occurred: " + ex.getMessage());
        }
    }
}
