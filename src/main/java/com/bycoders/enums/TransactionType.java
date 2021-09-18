package com.bycoders.enums;

import java.util.Arrays;

public enum TransactionType {

    DEBT(1, "+"),
    TICKET(2, "-"),
    FINANCING(3,"-"),
    CREDIT(4,"+"),
    LOAN_RECEIPT(5,"+"),
    SALES(6,"+"),
    TED_RECEIPT(7,"+"),
    DOC_RECEIPT(8,"+"),
    RENT(9,"-");

    private int value;
    private String arithmeticOperator;

    TransactionType(int value, String arithmeticOperator) {
        this.value = value;
        this.arithmeticOperator = arithmeticOperator;
    }

    public static TransactionType getTransactionTypeFromValue(int value){
        return Arrays.stream(TransactionType.values()).filter(f -> f.value == value).findFirst().orElseThrow(() -> new RuntimeException("Invalid Enum"));
    }
}
