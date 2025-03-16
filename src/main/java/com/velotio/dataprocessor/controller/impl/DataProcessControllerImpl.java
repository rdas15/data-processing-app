package com.velotio.dataprocessor.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.velotio.dataprocessor.controller.DataProcessController;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.service.DataIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
public class DataProcessControllerImpl implements DataProcessController {

    @Autowired
    private DataIngestionService dataIngestionService;

    @PostMapping(value = "/ingest", consumes = "application/json")
    @Override
    public ResponseEntity<Object> ingestData(@RequestBody CompanyDto companyDto) throws JsonProcessingException {
        System.out.println("Ingesting data for company: " + companyDto.getName());
        dataIngestionService.ingestData(companyDto);
        return ResponseEntity.ok().build();
    }


}
