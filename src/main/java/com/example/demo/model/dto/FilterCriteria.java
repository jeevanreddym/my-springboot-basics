package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterCriteria {
    List<SpecFilter> filters;
}
