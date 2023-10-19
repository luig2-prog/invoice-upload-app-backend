package com.fev.csvprocessor.infrastructure.dbo.h2.adapter;

import com.fev.csvprocessor.domain.common.exception.DefaultCustomException;
import com.fev.csvprocessor.domain.common.gateway.BillStorageGateway;
import com.fev.csvprocessor.domain.common.gateway.PageableQuery;
import com.fev.csvprocessor.domain.common.model.BillModel;
import com.fev.csvprocessor.infrastructure.dbo.h2.mapper.BillEntityMapper;
import com.fev.csvprocessor.infrastructure.dbo.h2.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class BillStorageGatewayAdapter implements BillStorageGateway {
    private final BillRepository billRepository;
    private final BillEntityMapper billEntityMapper = BillEntityMapper.getInstance();
    @Override
    public void saveAll(List<BillModel> billModel) throws DefaultCustomException {
        try {
            billRepository.saveAll(billModel.stream().map(billEntityMapper::map).toList());
        }catch (Exception e){
            log.error("error saving on db {} input: {}", e.getMessage(), billModel);
            throw new DefaultCustomException("Error saving bills", e);
        }
    }

    @Override
    public Page<BillModel> findAll(PageableQuery pageableQuery) {
        try {
            Pageable pageable = PageRequest.of(pageableQuery.getPage(), 10);
            return this.billRepository.findAll(pageable).map(billEntityMapper::reverseMap);
        }catch (Exception e){
            log.error("error getting bills from db {} input: {}", e.getMessage(), pageableQuery);
            throw new DefaultCustomException("Error getting bills", e);
        }

    }

    @Override
    public List<BillModel> findAll() {
        return this.billRepository.findAll().stream().map(billEntityMapper::reverseMap).toList();
    }

    @Override
    public void rejectBilling(BigInteger billCode) throws DefaultCustomException {
        try {
            if(this.billRepository.findById(billCode).orElse(null) == null){
                throw new DefaultCustomException("Bill not found");
            }
            this.billRepository.rejectBilling(billCode);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error updating bill status {} input: {}", e.getMessage(), billCode);
            throw new DefaultCustomException("Error updating bill status", e);
        }
    }
}
