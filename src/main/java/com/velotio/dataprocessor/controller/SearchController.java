package com.velotio.dataprocessor.controller;

import com.velotio.dataprocessor.model.CompanySearchFieldEnum;
import org.springframework.http.ResponseEntity;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.ProcessedCompany;

import java.util.List;

public interface SearchController {

    ResponseEntity<CompanyDto> searchCompany(String companyName);

    ResponseEntity<List<ProcessedCompany>> fts(CompanySearchFieldEnum field, String value);
}
