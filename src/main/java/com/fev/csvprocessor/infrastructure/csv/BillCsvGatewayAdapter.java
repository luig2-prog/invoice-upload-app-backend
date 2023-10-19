package com.fev.csvprocessor.infrastructure.csv;

import com.fev.csvprocessor.domain.common.gateway.BillCsvGateway;
import com.fev.csvprocessor.domain.common.model.BillModel;
import com.fev.csvprocessor.infrastructure.common.mapper.BillDtoMapper;
import com.fev.csvprocessor.infrastructure.csv.util.ReadCsvUtil;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
@Component
public class BillCsvGatewayAdapter implements BillCsvGateway {
    private final BillDtoMapper billDtoMapper = BillDtoMapper.getInstance();
    @Override
    public List<BillModel> extractDataFromFile(InputStream inputStream) {
        return ReadCsvUtil.readCsv(inputStream).stream().map(billDtoMapper::reverseMap).toList();
    }
}
