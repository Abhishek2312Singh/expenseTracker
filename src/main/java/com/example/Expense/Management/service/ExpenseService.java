package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ExpenseInputDto;
import com.example.Expense.Management.dto.ExpenseOutputDto;
import com.example.Expense.Management.dto.UserInputDto;
import com.example.Expense.Management.entity.Expense;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.enums.PaymentMode;
import com.example.Expense.Management.repository.ExpenseRepo;
import com.example.Expense.Management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private UserRepo userRepo;
//    public String addExpense(ExpenseInputDto expenseInputDto){
//        Expense expense = new Expense();
//        expense.setUser(new User(expenseInputDto.getUsername(), expenseInputDto.getMobile()));
//        expense.setExpenseName(expenseInputDto.getExpenseName());
//        expense.setDate(new Date());
//        expense.setAmount(expenseInputDto.getAmount());
//        expense.setPaymentMode(expenseInputDto.getPaymentMode());
//
//        expenseRepo.save(expense);
//        return "Expense Added!!";
//    }

    public String addExpense(ExpenseInputDto expenseInputDto, Principal principal){
        Expense expense = new Expense();
        expense.setExpenseName(expenseInputDto.getUsername());
        expense.setDate(LocalDate.now());
        expense.setAmount(expenseInputDto.getAmount());
        expense.setPaymentMode(expenseInputDto.getPaymentMode());
        expense.setUser(userRepo.findByUsername(principal.getName()).orElse(null));
        expenseRepo.save(expense);
        return "Expense Added!!!!";
    }

    public ExpenseOutputDto getExpenseById(Long id){
        Expense expense = expenseRepo.findById(id).orElse(null);
        ExpenseOutputDto expenseOutputDto = new ExpenseOutputDto();
        expenseOutputDto.setExpenseName(expense.getExpenseName());
        expenseOutputDto.setPaymentMode(expense.getPaymentMode());
        expenseOutputDto.setId(expense.getId());
        expenseOutputDto.setDate(expense.getDate());
        expenseOutputDto.setAmount(expense.getAmount());

        return expenseOutputDto;
    }
    public String getTotalAmt(){
        List<Expense> expenseList = expenseRepo.findAll();
        Double totalAmt = 0.0;
        for(Expense expense : expenseList){
            totalAmt = totalAmt + expense.getAmount();
        }
        return "Rs. " + totalAmt;
    }
    public List<ExpenseOutputDto> getExpenseByPaymentMode(String paymentMode){
        List<Expense> expenseList = expenseRepo.findByPaymentMode(PaymentMode.valueOf(paymentMode));
        List<ExpenseOutputDto> expenseOutputDtoList = new ArrayList<>();
        for(Expense expense : expenseList){
            ExpenseOutputDto expenseOutputDto = new ExpenseOutputDto();
            expenseOutputDto.setExpenseName(expense.getExpenseName());
            expenseOutputDto.setPaymentMode(expense.getPaymentMode());
            expenseOutputDto.setId(expense.getId());
            expenseOutputDto.setDate(expense.getDate());
            expenseOutputDto.setAmount(expense.getAmount());

            expenseOutputDtoList.add(expenseOutputDto);
        }
        return expenseOutputDtoList;
    }
    public List<ExpenseOutputDto> getByAmtRange(Double minAmt, Double maxAmt){
        List<Expense> expenseList = expenseRepo.findByAmountBetween(minAmt,maxAmt);
        List<ExpenseOutputDto> expenseOutputDtoList = new ArrayList<>();
        for(Expense expense : expenseList){
            ExpenseOutputDto expenseOutputDto = new ExpenseOutputDto();
            expenseOutputDto.setExpenseName(expense.getExpenseName());
            expenseOutputDto.setPaymentMode(expense.getPaymentMode());
            expenseOutputDto.setId(expense.getId());
            expenseOutputDto.setDate(expense.getDate());
            expenseOutputDto.setAmount(expense.getAmount());

            expenseOutputDtoList.add(expenseOutputDto);
        }
        return expenseOutputDtoList;
    }

}
