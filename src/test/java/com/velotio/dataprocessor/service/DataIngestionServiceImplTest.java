package com.velotio.dataprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velotio.dataprocessor.model.AddressDto;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.NotificationMessage;
import com.velotio.dataprocessor.model.ProductDto;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.repository.CompanyRepository;
import com.velotio.dataprocessor.service.impl.DataIngestionServiceImpl;
import com.velotio.dataprocessor.util.DtoTransformHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataIngestionServiceImplTest {

    @InjectMocks
    private DataIngestionServiceImpl dataIngestionService;

    @Mock
    private EventNotificationService eventNotificationService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private DtoTransformHelper dtoTransformHelper;


    @Test
    void testIngestData() throws JsonProcessingException {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Test Company");
        AddressDto addressDto = new AddressDto();
        ProductDto productDto = new ProductDto();
        companyDto.setAddresses(List.of(addressDto));
        companyDto.setProducts(List.of(productDto));
        Company company = new Company();
        company.setName("Test Company");
        company.setId(1L);
        NotificationMessage notificationMessage = new NotificationMessage("1",
                "Company Ingested");
        when(dtoTransformHelper.transformCompanyDtoToEntity(companyDto)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(company);
        dataIngestionService.ingestData(companyDto);
        verify(dtoTransformHelper, times(1)).transformCompanyDtoToEntity(companyDto);
        verify(companyRepository, times(1)).save(company);
    }
}