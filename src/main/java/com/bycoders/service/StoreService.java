package com.bycoders.service;

import com.bycoders.model.TransactionSummary;
import com.bycoders.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private UploadRepository uploadRepository;

    public List<TransactionSummary> getSummaryBalance(){
        return uploadRepository.getTransactionSummary();
    }
}
