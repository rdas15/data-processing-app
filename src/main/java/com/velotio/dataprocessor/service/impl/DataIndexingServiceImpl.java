package com.velotio.dataprocessor.service.impl;

import com.velotio.dataprocessor.service.DataIndexingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velotio.dataprocessor.model.ProcessedCompany;
import com.velotio.dataprocessor.repository.opensearch.ProcessedCompanyRepository;

@Service
public class DataIndexingServiceImpl implements DataIndexingService {

    @Autowired
    private ProcessedCompanyRepository processedCompanyRepository;

    @Override
    public void indexData(ProcessedCompany company) {
        processedCompanyRepository.save(company);
    }
}
