package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.Expense;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.enums.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    List<Expense> findByUserAndPaymentMode(User user,PaymentMode paymentMode);
    List<Expense> findByUserAndAmountBetween(User user, Double minAmt, Double maxAmt);
}
