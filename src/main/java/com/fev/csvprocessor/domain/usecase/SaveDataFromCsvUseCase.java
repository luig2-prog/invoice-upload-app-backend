package com.fev.csvprocessor.domain.usecase;

import com.fev.csvprocessor.domain.common.exception.DefaultCustomException;
import com.fev.csvprocessor.domain.common.gateway.BillCsvGateway;
import com.fev.csvprocessor.domain.common.gateway.BillStorageGateway;
import com.fev.csvprocessor.domain.common.model.BillModel;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
public class SaveDataFromCsvUseCase {
    private final BillStorageGateway billStorageGateway;
    private final BillCsvGateway billCsvGateway;
    public String saveAll(InputStream inputStream) throws DefaultCustomException {
        List<BillModel>  billModel = billCsvGateway.extractDataFromFile(inputStream);
        billStorageGateway.saveAll(billModel);
        return "success";
    }

}
