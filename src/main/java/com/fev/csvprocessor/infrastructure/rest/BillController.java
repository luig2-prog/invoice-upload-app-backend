package com.fev.csvprocessor.infrastructure.rest;

import com.fev.csvprocessor.domain.common.gateway.PageableQuery;
import com.fev.csvprocessor.domain.usecase.ListSavedBillsUseCase;
import com.fev.csvprocessor.domain.usecase.RejectUseCase;
import com.fev.csvprocessor.domain.usecase.SaveDataFromCsvUseCase;
import com.fev.csvprocessor.infrastructure.common.dto.BillDto;
import com.fev.csvprocessor.infrastructure.common.dto.ResponseDto;
import com.fev.csvprocessor.infrastructure.common.mapper.BillDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {
    private final ListSavedBillsUseCase listSavedBillsUseCase;
    private final SaveDataFromCsvUseCase saveDataFromCsvUseCase;
    private final RejectUseCase rejectUseCase;
    private final BillDtoMapper billDtoMapper = BillDtoMapper.getInstance();

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<Page<BillDto>>> listData(PageableQuery pageableQuery){
        log.info("Listing data");
        return ResponseEntity.ok(new ResponseDto<>(this.listSavedBillsUseCase.findAll(pageableQuery).map(billDtoMapper::map), null));
    }

    @GetMapping("/list-all")
    public ResponseEntity<ResponseDto<List<BillDto>>> listAllData(){
        log.info("Listing data");
        return ResponseEntity.ok(new ResponseDto<>(this.listSavedBillsUseCase.findAll().stream().map(billDtoMapper::map).toList(), null));
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDto<String>> saveData(@RequestParam("file")MultipartFile file){
        log.info("Saving data");
        String response = "Error saving data";
        try{
            response = saveDataFromCsvUseCase.saveAll(file.getInputStream());
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(new ResponseDto<>(response, null));
    }
    @PatchMapping("/reject/{billCode}")
    public ResponseEntity<ResponseDto<String>> rejectBilling(@PathVariable("billCode") BigInteger billCode){
        log.info("Rejecting billing");
        rejectUseCase.rejectBilling(billCode);
        return ResponseEntity.ok(new ResponseDto<>("Billing rejected", null));
    }
}
