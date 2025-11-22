package com.example.Expense.Management.service;

import com.example.Expense.Management.entity.PasswordResetToken;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.repository.PasswordResetTokenRepo;
import com.example.Expense.Management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;
    @Autowired
    private JavaMailSender javaMailSender;

    public void generateToken(String email){
        User user = userRepo.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with email : " + email));
        String token = UUID.randomUUID().toString();

        PasswordResetToken existingToken = passwordResetTokenRepo.findByUser(user);
        if(existingToken != null){
            existingToken.setExpiryDateTime(LocalDateTime.now().plusMinutes(3));
            existingToken.setUser(user);
            existingToken.setToken(token);
            passwordResetTokenRepo.save(existingToken);
        }else {
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDateTime(LocalDateTime.now().plusMinutes(15));
            passwordResetTokenRepo.save(resetToken);
        }
        sendResetMail(email,token);
    }
    private void sendResetMail(String email, String token){
        String link = "http://localhost:80/auth/resetPassword?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Your Password!!");
        message.setText("Click the link below to reset password:\n" + link);
        javaMailSender.send(message);
    }
}
