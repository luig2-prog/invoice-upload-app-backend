package com.fev.csvprocessor.domain.common.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillModel {
    private BigInteger billCode;
    private String name;
    private String lastName;
    private String address;
    private BigDecimal amount;
    private LocalDate paymentExpirationDate;
    private LocalDate paymentDueDate;
    private String status;
}
