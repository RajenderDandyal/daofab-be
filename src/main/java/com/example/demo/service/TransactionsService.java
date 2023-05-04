package com.example.demo.service;

import com.example.demo.dto.ResponseTransaction;
import com.example.demo.dto.Transaction;

public interface TransactionsService {
    ResponseTransaction fetchTransactions(String pageNumber);
    Transaction fetchTransaction(int id);

}
