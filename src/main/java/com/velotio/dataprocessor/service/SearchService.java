package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.CompanySearchFieldEnum;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.ProcessedCompany;

import java.util.List;

public interface SearchService {

    CompanyDto searchCompany(String companyName);

    List<ProcessedCompany> searchByField(CompanySearchFieldEnum field, String value);
}
