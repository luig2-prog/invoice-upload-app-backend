package com.fev.csvprocessor.infrastructure.dbo.h2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger billCode;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50) default 'N/A'")
    private String name;
    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(50) default 'N/A'")
    private String lastName;
    @Column(name = "address", columnDefinition = "varchar(200)")
    private String address;
    @Column(name = "amount", nullable = false, columnDefinition = "decimal(10,2) default 0.00")
    private BigDecimal amount;
    @Column(name = "payment_expiration_date", nullable = false)
    private LocalDate paymentExpirationDate;
    @Column(name = "payment_due_date", nullable = false)
    private LocalDate paymentDueDate;
    @Column(name = "status", nullable = false, columnDefinition = "varchar(20) default 'pendiente'")
    private String status;
}
