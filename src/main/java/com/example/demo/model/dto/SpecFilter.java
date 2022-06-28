package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpecFilter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;//Used in case of IN operator
}
