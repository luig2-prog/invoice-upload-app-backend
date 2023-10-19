package com.fev.csvprocessor.infrastructure.dbo.h2.repository;

import com.fev.csvprocessor.infrastructure.dbo.h2.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

public interface BillRepository extends JpaRepository<BillEntity, BigInteger> {
    @Modifying
    @Transactional
    @Query("UPDATE BillEntity b SET b.status = 'Rechazada' WHERE b.billCode = :billCode")
    void rejectBilling(@Param("billCode") BigInteger billCode);
}
