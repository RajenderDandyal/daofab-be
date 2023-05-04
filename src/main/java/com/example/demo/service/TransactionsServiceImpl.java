package com.example.demo.service;

import com.example.demo.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionsServiceImpl implements TransactionsService {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public ResponseTransaction fetchTransactions(String pageNumber) {
        try {
            File parentFile = new ClassPathResource("Parent.json").getFile();
            Parent parent = mapper.readValue(parentFile, Parent.class);

            File childFile = new ClassPathResource("Child.json").getFile();
            Child child = mapper.readValue(childFile, Child.class);

            List<ParentTransaction> parentTransactions = parent.getData();
            List<Transaction> transactions = new ArrayList<>();

            parentTransactions.forEach(transaction ->{
               List<ChildTransaction> instalments = child.getData().stream().filter(inst-> inst.getParentId() == transaction.getId()).collect(Collectors.toList());
               Transaction transaction1 = new Transaction(transaction, instalments);
               transactions.add(transaction1);
            });

            ResponseTransaction responseTransaction = new ResponseTransaction();

            int pageNum = Integer.parseInt(pageNumber);
            int strtInd = pageNum == 0? 0: pageNum==1?pageNum+1:pageNum+2;// offset == 0 ? 0: offset == 2? 2: offset-2;
            int toInd = strtInd + 2;

            responseTransaction.setTransactions(transactions.subList(strtInd, toInd));
            responseTransaction.setPageNum(pageNum);
            responseTransaction.setTotalPages(transactions.size()%2 ==0 ? (int)transactions.size()/2 : (int)Math.floor(transactions.size()/2) + 1);
            responseTransaction.setPerPageRecords(2);

            return responseTransaction;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction fetchTransaction(int id) {
        try {
            File parentFile = new ClassPathResource("Parent.json").getFile();
            Parent parent = mapper.readValue(parentFile, Parent.class);

            File childFile = new ClassPathResource("Child.json").getFile();
            Child child = mapper.readValue(childFile, Child.class);

            List<ParentTransaction> parentTransactions = parent.getData();
            ParentTransaction transaction = parentTransactions.stream().filter(item -> item.getId()==id).collect(Collectors.toList()).get(0);
            List<ChildTransaction> instalments = child.getData().stream().filter(inst -> inst.getParentId() == transaction.getId()).collect(Collectors.toList());
            return new Transaction(transaction,instalments);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
