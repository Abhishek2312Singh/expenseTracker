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
@CrossOrigin("http://localhost:3000")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @GetMapping("/getExpense")
    public ResponseEntity<List<ExpenseOutputDto>> getExpenseById(Principal principal){
        return ResponseEntity.ok(expenseService.getExpense(principal));
    }
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
