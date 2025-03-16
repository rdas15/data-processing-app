package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.CompanySearchFieldEnum;
import com.velotio.dataprocessor.model.ProcessedCompany;
import com.velotio.dataprocessor.model.ProcessedProduct;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.repository.CompanyRepository;
import com.velotio.dataprocessor.repository.opensearch.ProcessedCompanyRepository;
import com.velotio.dataprocessor.service.impl.SearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ProcessedCompanyRepository processedCompanyRepository;

    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    void testSearchCompany() {
        Company company = new Company();
        company.setName("TestCompany");
        when(companyRepository.findByName("TestCompany")).thenReturn(company);
        CompanyDto result = searchService.searchCompany("TestCompany");
        assertNotNull(result);
        verify(companyRepository, times(1)).findByName("TestCompany");
    }

    @Test
    void testSearchByFieldProductNameSuccess() {
        ProcessedCompany processedCompany = new ProcessedCompany();
        processedCompany.setName("TestCompany");
        ProcessedProduct processedProduct = new ProcessedProduct();
        processedProduct.setName("TestProduct");
        processedCompany.setProducts(List.of(processedProduct));
        List<ProcessedCompany> companies = List.of(processedCompany);
        when(processedCompanyRepository.findByProductName("TestProduct")).thenReturn(companies);
        List<ProcessedCompany> result = searchService.searchByField(CompanySearchFieldEnum.PRODUCT_NAME, "TestProduct");
        assertEquals(1, result.size());
        verify(processedCompanyRepository, times(1)).findByProductName("TestProduct");
    }

    @Test
    void testSearchByFieldCompanyNameSuccess() {
        ProcessedCompany processedCompany = new ProcessedCompany();
        processedCompany.setName("TestCompany");
        ProcessedProduct processedProduct = new ProcessedProduct();
        processedProduct.setName("TestProduct");
        processedCompany.setProducts(List.of(processedProduct));
        List<ProcessedCompany> companies = List.of(processedCompany);
        when(processedCompanyRepository.findByNameContaining("TestCompany")).thenReturn(companies);
        List<ProcessedCompany> result = searchService.searchByField(CompanySearchFieldEnum.COMPANY_NAME, "TestCompany");
        assertEquals(1, result.size());
        verify(processedCompanyRepository, times(1)).findByNameContaining("TestCompany");
    }


}
