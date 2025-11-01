package com.example.Expense.Management.dto;

import com.example.Expense.Management.enums.PaymentMode;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseInputDto {
    private String expenseName;
    private PaymentMode paymentMode;
    private Double amount;
}
