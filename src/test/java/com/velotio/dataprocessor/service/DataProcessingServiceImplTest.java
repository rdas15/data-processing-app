package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.NotificationMessage;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.repository.CompanyRepository;
import com.velotio.dataprocessor.service.impl.DataProcessingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DataProcessingServiceImplTest {

    @InjectMocks
    private DataProcessingServiceImpl dataProcessingService;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EventNotificationService eventNotificationService;

    @Mock
    private DataIndexingService dataIndexingService;

    @Test
    void testProcessRawData() {
        Company company = new Company();
        company.setId(1L);
        NotificationMessage message = new NotificationMessage();
        message.setCompanyId("1");
        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));
        dataProcessingService.processRawData(message);
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCompanyById() {
        Company company = new Company();
        company.setId(1L);
        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));
        Company result = dataProcessingService.getCompanyById(1L);
        assertNotNull(result);
        verify(companyRepository, times(1)).findById(1L);
    }
}

