package com.example.Expense.Management.controller;

import com.example.Expense.Management.dto.UserInputDto;
import com.example.Expense.Management.repository.UserRepo;
import com.example.Expense.Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody UserInputDto userInputDto){
        return ResponseEntity.ok(userService.addUser(userInputDto));
    }
    @GetMapping("/getusername")
    public ResponseEntity<String> getUsername(Principal principal){
        return ResponseEntity.ok(userService.getUsername(principal));
    }
}
