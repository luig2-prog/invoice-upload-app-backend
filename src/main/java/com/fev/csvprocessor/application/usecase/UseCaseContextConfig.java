package com.fev.csvprocessor.application.usecase;

import com.fev.csvprocessor.domain.common.gateway.BillCsvGateway;
import com.fev.csvprocessor.domain.common.gateway.BillStorageGateway;
import com.fev.csvprocessor.domain.usecase.ListSavedBillsUseCase;
import com.fev.csvprocessor.domain.usecase.RejectUseCase;
import com.fev.csvprocessor.domain.usecase.SaveDataFromCsvUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class UseCaseContextConfig {
    @Bean
    public ListSavedBillsUseCase listSavedBillsUseCaseBean(BillStorageGateway billStorageGateway){
        return new ListSavedBillsUseCase(billStorageGateway);
    }
    @Bean
    public SaveDataFromCsvUseCase saveDataFromCsvUseCaseBean(BillStorageGateway billStorageGateway, BillCsvGateway billCsvGateway){
        return new SaveDataFromCsvUseCase(billStorageGateway, billCsvGateway);
    }
    @Bean
    public RejectUseCase rejectUseCaseBean(BillStorageGateway billStorageGateway){
        return new RejectUseCase(billStorageGateway);
    }
}
