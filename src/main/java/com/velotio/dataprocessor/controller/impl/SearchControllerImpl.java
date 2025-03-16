package com.velotio.dataprocessor.controller.impl;

import com.velotio.dataprocessor.controller.SearchController;
import com.velotio.dataprocessor.model.CompanySearchFieldEnum;
import com.velotio.dataprocessor.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.ProcessedCompany;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchControllerImpl implements SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping(value = "/company", produces = "application/json")
    @Override
    public ResponseEntity<CompanyDto> searchCompany(String companyName) {
        return ResponseEntity.ok(searchService.searchCompany(companyName));
    }

    @Operation(summary = "Search companies by field, " +
            "Supported field enums are - companyName(Text), companyShortName(Text), " +
            "productName(Keyword), productDescription(Text), addressLineItem(Text). " +
            "Only Text fields allow FTS. Keywords fields can match exact value.")
    @GetMapping(value = "/fts", produces = "application/json")
    @Override
    public ResponseEntity<List<ProcessedCompany>> fts(CompanySearchFieldEnum field, String value) {
        return ResponseEntity.ok(searchService.searchByField(field, value));
    }
}
