package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Integer countByUsernameStartingWith(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
