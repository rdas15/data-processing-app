package com.velotio.dataprocessor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.velotio.dataprocessor.model.CompanyDto;
import org.springframework.http.ResponseEntity;

public interface DataProcessController {

    ResponseEntity<Object> ingestData(CompanyDto companyDto) throws JsonProcessingException;
}
