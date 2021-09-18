package com.bycoders.repository;

import com.bycoders.model.TransactionDocument;
import com.bycoders.model.TransactionSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadRepository extends CrudRepository<TransactionDocument, Long> {

    @Query(value = "select new com.bycoders.model.TransactionSummary(t.storeName, sum(t.transactionValue) as storeBalance) " +
            "from TransactionDocument t group by t.storeName")
    List<TransactionSummary> getTransactionSummary();
}
