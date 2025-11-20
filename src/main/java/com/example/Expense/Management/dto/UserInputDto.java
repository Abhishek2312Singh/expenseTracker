package com.example.Expense.Management.dto;

import lombok.Data;

@Data
public class UserInputDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private String password;
    private String username;
}
