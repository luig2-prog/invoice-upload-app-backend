package com.fev.csvprocessor.infrastructure.common.mapper;

import com.fev.csvprocessor.domain.common.model.BillModel;
import com.fev.csvprocessor.infrastructure.common.dto.BillDto;
import com.fev.csvprocessor.infrastructure.common.mapper.base.IMapper;

public class BillDtoMapper implements IMapper<BillDto, BillModel> {
    private static BillDtoMapper instance;

    private BillDtoMapper() {
    }

    public static synchronized BillDtoMapper getInstance() {
        if (instance == null) {
            instance = new BillDtoMapper();
        }
        return instance;
    }

    @Override
    public BillDto map(BillModel billModel) {
        if (billModel == null) {
            return null;
        }
        return BillDto.builder()
                .billCode(billModel.getBillCode())
                .name(billModel.getName())
                .lastName(billModel.getLastName())
                .address(billModel.getAddress())
                .amount(billModel.getAmount())
                .paymentDueDate(billModel.getPaymentDueDate())
                .paymentExpirationDate(billModel.getPaymentExpirationDate())
                .status(billModel.getStatus())
                .build();
    }

    @Override
    public BillModel reverseMap(BillDto billDto) {
        if (billDto == null) {
            return null;
        }
        return new BillModel(
                billDto.getBillCode(),
                billDto.getName(),
                billDto.getLastName(),
                billDto.getAddress(),
                billDto.getAmount(),
                billDto.getPaymentExpirationDate(),
                billDto.getPaymentDueDate(),
                billDto.getStatus());
    }
}
