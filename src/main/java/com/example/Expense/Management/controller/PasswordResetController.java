package com.example.Expense.Management.controller;

import com.example.Expense.Management.config.SecurityConfig;
import com.example.Expense.Management.entity.PasswordResetToken;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.repository.PasswordResetTokenRepo;
import com.example.Expense.Management.repository.UserRepo;
import com.example.Expense.Management.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {
    @Autowired
    private PasswordResetService resetService;

    @Autowired
    private SecurityConfig config;

    @Autowired
    private PasswordResetTokenRepo tokenRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        resetService.generateToken(email);
        return ResponseEntity.ok("Reset password mail sended!!");
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String newPassword,
                                                @RequestParam String confirmPassword){
        PasswordResetToken resetToken = tokenRepo.findByToken(token);
        if(token == null){
            return ResponseEntity.badRequest().body("Invalid Token!!");
        }
        if(resetToken.getExpiryDateTime().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("Link Expired!!");
        }
        if(!newPassword.equals(confirmPassword)){
            return ResponseEntity.badRequest().body("Password Not Matched!!");
        }
        User user = resetToken.getUser();
        user.setPassword(config.encoder().encode(newPassword));
        userRepo.save(user);
        tokenRepo.delete(resetToken);

        return ResponseEntity.ok("Password Reset Successful!!");
    }
}
