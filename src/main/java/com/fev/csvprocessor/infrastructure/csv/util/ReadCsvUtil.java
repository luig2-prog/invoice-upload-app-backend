package com.fev.csvprocessor.infrastructure.csv.util;

import com.fev.csvprocessor.domain.common.exception.CustomIllegalArgumentException;
import com.fev.csvprocessor.domain.common.exception.DefaultCustomException;
import com.fev.csvprocessor.infrastructure.common.dto.BillDto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public final class ReadCsvUtil {
    private ReadCsvUtil() {
        // empty constructor
    }

    public static List<BillDto> readCsv(InputStream file) {
        List<BillDto> bankAccountList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
            Reader reader = new InputStreamReader(file);
            CSVReader csvReader = new CSVReader(reader);  // Reading All Records at once into a List<String[]>
            String[] nextRecord;
            boolean firstLine = true;
            while ((nextRecord = csvReader.readNext()) != null) {
                if(firstLine){
                    validateHeaders(nextRecord);
                    firstLine = false;
                    continue;
                }
                bankAccountList.add(BillDto.builder().billCode(BigInteger.valueOf(Long.parseLong(nextRecord[0])))
                        .name(nextRecord[1])
                        .lastName(nextRecord[2])
                        .address(nextRecord[3])
                        .amount(BigDecimal.valueOf(Float.parseFloat(nextRecord[4])))
                        .paymentExpirationDate(LocalDate.parse(nextRecord[5], dtf))
                        .paymentDueDate(LocalDate.parse(nextRecord[6], dtf))
                        .status(nextRecord[7])
                        .build());
            }
        } catch (IOException | CsvValidationException e) {
            log.error(e.getMessage());
            throw new DefaultCustomException(e.getMessage(), e);
        }
        return bankAccountList;
    }
    private static void validateHeaders(String[] headers){
        if(headers.length != Constant.CSV_HEADERS.length){
            throw new CustomIllegalArgumentException("Invalid csv headers", "validateHeaders", headers);
        }
        for (int i = 0; i < headers.length - 1 ; i++) {
            if(!headers[i].equals(Constant.CSV_HEADERS[i])){
                throw new CustomIllegalArgumentException("Invalid csv headers", "validateHeaders", headers);
            }
        }
    }
}
