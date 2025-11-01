package com.example.Expense.Management.repository;

import com.example.Expense.Management.entity.Expense;
import com.example.Expense.Management.enums.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    List<Expense> findByPaymentMode(PaymentMode paymentMode);
    List<Expense> findByAmountBetween(Double minAmt, Double maxAmt);
}
