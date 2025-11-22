package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.PasswordResetToken;
import com.example.Expense.Management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
