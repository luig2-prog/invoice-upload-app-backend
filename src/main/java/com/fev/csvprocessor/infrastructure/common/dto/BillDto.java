package com.fev.csvprocessor.infrastructure.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
@Builder(toBuilder = true)
@ToString
@Getter
public class BillDto {
    private BigInteger billCode;
    private String name;
    private String lastName;
    private String address;
    private BigDecimal amount;
    private LocalDate paymentExpirationDate;
    private LocalDate paymentDueDate;
    private String status;
}
