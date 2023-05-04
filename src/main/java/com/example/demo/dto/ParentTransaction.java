package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentTransaction {
    private int id;
    private String sender;
    private String receiver;
    private Long totalAmount;
}
