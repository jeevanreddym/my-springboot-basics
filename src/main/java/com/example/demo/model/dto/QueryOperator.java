package com.example.demo.model.dto;

public enum QueryOperator {
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    IN("IN"),
    LIKE("LIKE"),
    GREATER_THAN_EQUAL(">="),
    LESS_THAN_EQUAL("<="),
    MATCH(""),
    MATCH_START(""),
    MATCH_END(""),
    NOT_IN("");



    String operator;
    QueryOperator(String operator) {

    }
}
