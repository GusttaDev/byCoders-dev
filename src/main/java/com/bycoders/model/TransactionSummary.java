package com.bycoders.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionSummary {

    private String storeName;
    private Double storeBalance;

    public TransactionSummary(String storeName, Double storeBalance) {
        this.storeName = storeName;
        this.storeBalance = new BigDecimal(storeBalance).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getStoreBalance() {
        return storeBalance;
    }

    public void setStoreBalance(Double storeBalance) {
        this.storeBalance = storeBalance;
    }
}
