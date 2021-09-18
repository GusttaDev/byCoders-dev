package com.bycoders.service;

import com.bycoders.enums.TransactionType;
import com.bycoders.repository.UploadRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UploadServiceTest {

    @Spy
    @InjectMocks
    private UploadService uploadService;

    @Mock
    private UploadRepository uploadRepository;

    private static final int LOAN_RECEIPT = 5;

    @Test
    public void readFile_success(){

        MockMultipartFile file = new MockMultipartFile("data", "CNAB.txt", MediaType.TEXT_PLAIN_VALUE, "some other type".getBytes());

        uploadService.readFile(file);
        verify(uploadService, times(21)).parserToObject(any());
        verify(uploadRepository, times(21)).save(any());
    }

    @Test
    public void parseToObject_whenInformedLine_shouldReturnTransactionDocument(){

        var line = "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ";
        var storeOwner = "MARIA JOSEFINA";
        var storeName = "LOJA DO Ó - MATRIZ";
        var response = uploadService.parserToObject(line);

        assertThat(response.getType()).isEqualTo(TransactionType.getTransactionTypeFromValue(LOAN_RECEIPT));
        assertThat(response.getStoreOwner()).isEqualTo(storeOwner);
        assertThat(response.getStoreName()).isEqualTo(storeName);
    }

    @Test
    public void adjustValue_whenArithmeticOperatorIsPositive_shouldReturnPositiveValue(){

        var value = 10.0;
        var arithmeticOperator = "+";

        var response = uploadService.adjustValue(value, arithmeticOperator);

        assertThat(response).isEqualTo(value);
    }

    @Test
    public void adjustValue_whenArithmeticOperatorIsNegative_shouldReturnNegativeValue(){

        var value = 10.0;
        var arithmeticOperator = "-";

        var response = uploadService.adjustValue(value, arithmeticOperator);

        assertThat(response).isEqualTo(-10);
    }
}
