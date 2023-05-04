package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransaction {
    private List<Transaction> transactions;
    private int pageNum;
    private int totalPages;
    private int perPageRecords;
}
