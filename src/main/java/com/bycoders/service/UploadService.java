package com.bycoders.service;

import com.bycoders.enums.TransactionType;
import com.bycoders.model.TransactionDocument;
import com.bycoders.repository.UploadRepository;
import com.bycoders.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class UploadService {

    public static final double ONE_HUNDRED = 100.00;
    public static final int MINUS_ONE = -1;

    @Autowired
    private UploadRepository uploadRepository;

    public void readFile(MultipartFile file) {

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        var fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();


        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {

            String line = br.readLine();
            while (line != null) {
                TransactionDocument transactionDocument = parserToObject(line);
                save(transactionDocument);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TransactionDocument parserToObject(String line) {

        TransactionDocument transactionDocument = new TransactionDocument();

        TransactionType transactionTypeFromValue = TransactionType.getTransactionTypeFromValue(Integer.parseInt(line.substring(0, 1)));
        String arithmeticOperator = transactionTypeFromValue.getArithmeticOperator();

        transactionDocument.setType(transactionTypeFromValue);
        transactionDocument.setTransactionDate(Util.stringToLocalDate(line.substring(1, 9)));

        var value = Double.parseDouble(line.substring(9, 19)) / ONE_HUNDRED;
        Double finalValue = adjustValue(value, arithmeticOperator);

        transactionDocument.setTransactionValue(finalValue);
        transactionDocument.setCpf(line.substring(19, 30));
        transactionDocument.setCard(line.substring(30, 42));
        transactionDocument.setHour(Util.formatHour(line.substring(42, 48)));
        transactionDocument.setStoreOwner(line.substring(48, 62).trim());
        transactionDocument.setStoreName(line.substring(62, 80).trim());

        return transactionDocument;
    }

    public Double adjustValue(Double value, String arithmeticOperator) {
        if ("-".equalsIgnoreCase(arithmeticOperator)) {
            return value * MINUS_ONE;
        }
        return value;
    }

    public void save(TransactionDocument transactionDocument) {
        uploadRepository.save(transactionDocument);
    }
}
