package com.fev.csvprocessor.domain.common.gateway;

import com.fev.csvprocessor.domain.common.model.BillModel;

import java.io.InputStream;
import java.util.List;

public interface BillCsvGateway {
    List<BillModel> extractDataFromFile(InputStream inputStream);
}
