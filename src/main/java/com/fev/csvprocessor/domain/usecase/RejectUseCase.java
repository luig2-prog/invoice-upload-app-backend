package com.fev.csvprocessor.domain.usecase;

import com.fev.csvprocessor.domain.common.gateway.BillStorageGateway;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor
public class RejectUseCase {
    private final BillStorageGateway billStorageGateway;

    public void rejectBilling(BigInteger billCode){
        billStorageGateway.rejectBilling(billCode);
    }
}
