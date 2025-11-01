package com.example.Expense.Management.dto;

import com.example.Expense.Management.enums.PaymentMode;
import lombok.Data;

import java.util.Date;

@Data
public class ExpenseOutputDto {
    private Long id;
    private String expenseName;
    private Date date;
    private PaymentMode paymentMode;
    private Double amount;
}
