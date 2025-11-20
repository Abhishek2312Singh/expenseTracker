package com.example.Expense.Management.dto;

import com.example.Expense.Management.enums.PaymentMode;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ExpenseOutputDto {
    private Long id;
    private String expenseName;
    private LocalDate date;
    private PaymentMode paymentMode;
    private Double amount;
}
