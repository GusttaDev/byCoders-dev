package com.bycoders.resource;

import com.bycoders.model.TransactionSummary;
import com.bycoders.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreResource {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public ResponseEntity<List<TransactionSummary>> getSummaryBalance(){
        return ResponseEntity.ok(storeService.getSummaryBalance());
    }
}
