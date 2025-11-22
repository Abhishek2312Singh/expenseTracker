package com.example.Expense.Management.controller;

import com.example.Expense.Management.dto.UserInputDto;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam String currentPassword,
                                                 @RequestParam String newPassword,
                                                 @RequestParam String confirmPassword,
                                                 @AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.changePassword(currentPassword,newPassword,confirmPassword,user));
    }
}
