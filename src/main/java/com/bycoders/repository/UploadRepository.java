package com.bycoders.repository;

import com.bycoders.model.TransactionDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends CrudRepository<TransactionDocument, Long> {
}
