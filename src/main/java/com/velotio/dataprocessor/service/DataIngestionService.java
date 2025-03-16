package com.velotio.dataprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.entity.Company;

public interface DataIngestionService {

    void ingestData(CompanyDto companyDto) throws JsonProcessingException;
}
