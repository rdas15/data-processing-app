package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.ProcessedCompany;

public interface DataIndexingService {

    void indexData(ProcessedCompany company);
}
