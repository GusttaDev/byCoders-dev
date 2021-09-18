package com.bycoders.service;

import com.bycoders.enums.TransactionType;
import com.bycoders.model.TransactionDocument;
import com.bycoders.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class UploadService {

    public static final double ONE_HUNDRED = 100.00;

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

    private TransactionDocument parserToObject(String line) {

        TransactionDocument transactionDocument = new TransactionDocument();

        transactionDocument.setType(TransactionType.getTransactionTypeFromValue(Integer.parseInt(line.substring(0, 1))));
        transactionDocument.setTransactionDate(Util.stringToLocalDate(line.substring(1, 9)));
        transactionDocument.setTransactionValue(Double.parseDouble(line.substring(9, 19)) / ONE_HUNDRED);
        transactionDocument.setCpf(line.substring(19, 30));
        transactionDocument.setCard(line.substring(30, 42));
        transactionDocument.setHour(Util.formatHour(line.substring(42, 48)));
        transactionDocument.setStoreOwner(line.substring(48, 62));
        transactionDocument.setStoreName(line.substring(62, 80));

        return transactionDocument;
    }

    private void save(TransactionDocument transactionDocument){

    }
}
