package com.example.Expense.Management.controller;

import com.example.Expense.Management.dto.ExpenseInputDto;
import com.example.Expense.Management.dto.ExpenseOutputDto;
import com.example.Expense.Management.dto.UserInputDto;
import com.example.Expense.Management.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @GetMapping("/getExpense/{id}")
    public ResponseEntity<ExpenseOutputDto> getExpenseById(@PathVariable Long id){
        return new ResponseEntity<>(expenseService.getExpenseById(id),HttpStatusCode.valueOf(200));
    }
//    @PostMapping("/addExpense")
//    public ResponseEntity<String> addExpense(@RequestBody ExpenseInputDto expenseInputDto){
//        return ResponseEntity.ok(expenseService.addExpense(expenseInputDto));
//    }
    @GetMapping("/totalExpense")
    public ResponseEntity<String> getTotalExpense(){
        return  ResponseEntity.ok(expenseService.getTotalAmt());
    }
    @GetMapping("/paymentMode")
    public ResponseEntity<List<ExpenseOutputDto>> getExpenseByPaymentMode(@RequestParam String paymentMode){
        return new ResponseEntity<>(expenseService.getExpenseByPaymentMode(paymentMode), HttpStatusCode.valueOf(200));
    }
    @GetMapping("/expenseByRange")
    public ResponseEntity<List<ExpenseOutputDto>> getExpenseByRange(@RequestParam Double minAmt, @RequestParam Double maxAmt){
        return ResponseEntity.ok(expenseService.getByAmtRange(minAmt,maxAmt));
    }

    @PostMapping("/addexpense")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseInputDto expenseInputDto, Principal principal){
        return ResponseEntity.ok(expenseService.addExpense(expenseInputDto,principal));
    }
}
