package com.example.Expense.Management.entity;

import com.example.Expense.Management.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "expenses")
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String expenseName;
    private Date date;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    private Double amount;
}
