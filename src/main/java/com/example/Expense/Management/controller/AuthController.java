package com.example.Expense.Management.controller;

import com.example.Expense.Management.dto.UserInputDto;
import com.example.Expense.Management.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager manager;
    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody UserInputDto userInputDto){
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    userInputDto.getUsername(),
                    userInputDto.getPassword()
            ));
            return ResponseEntity.ok(jwtUtils.generateToken(userInputDto.getUsername()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("User Not Found!!!");
        }
    }
}
