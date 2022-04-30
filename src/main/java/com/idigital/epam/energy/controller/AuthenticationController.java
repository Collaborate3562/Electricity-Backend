package com.idigital.epam.energy.controller;

import com.idigital.epam.energy.entity.User;
import com.idigital.epam.energy.repository.HomeRepository;
import com.idigital.epam.energy.security.Token;
import com.idigital.epam.energy.security.UserMaxsus;
import com.idigital.epam.energy.service.HomeService;
import com.idigital.epam.energy.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@CrossOrigin(maxAge = 3600)
public class AuthenticationController {


    @Autowired
    private UserService userService;


    @Autowired
    HomeService homeService;

    @PostMapping("/authentication")
    public ResponseEntity<?> signInToTheServer(@RequestBody UserMaxsus userMaxsus) throws Exception {
        return ResponseEntity.ok(new Token(userService.authentication(userMaxsus)));
    }


    @PostMapping("/registration")
    public ResponseEntity<?> signUpToServer(@RequestBody UserMaxsus userMaxsus) throws Exception {


        return new ResponseEntity(userService.create(Long.parseLong(userMaxsus.getUsername()), userMaxsus.getPassword()), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<User> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}