package com.example.demo.controller;

import com.example.demo.dto.ResponseTransaction;
import com.example.demo.dto.Transaction;
import com.example.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/api/transactions")
    public ResponseEntity<ResponseTransaction> fetchTransactions(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") String pageNumber){
        return new ResponseEntity<>(transactionsService.fetchTransactions(pageNumber), HttpStatus.OK);
    }
    @GetMapping("/api/transactions/{id}")
    public ResponseEntity<Transaction> fetchTransaction(@PathVariable(value = "id", required = true) int id){
        return new ResponseEntity<>(transactionsService.fetchTransaction(id), HttpStatus.OK);
    }
}
