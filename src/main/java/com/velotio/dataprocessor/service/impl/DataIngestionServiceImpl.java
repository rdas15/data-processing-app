package com.velotio.dataprocessor.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velotio.dataprocessor.model.NotificationMessage;
import com.velotio.dataprocessor.model.entity.Address;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.model.entity.Product;
import com.velotio.dataprocessor.util.DtoTransformHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velotio.dataprocessor.model.AddressDto;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.ProductDto;
import com.velotio.dataprocessor.repository.CompanyRepository;
import com.velotio.dataprocessor.service.DataIngestionService;
import com.velotio.dataprocessor.service.EventNotificationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataIngestionServiceImpl implements DataIngestionService {

    @Autowired
    private EventNotificationService eventNotificationService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DtoTransformHelper dtoTransformHelper;


    @Override
    public void ingestData(CompanyDto companyDto) throws JsonProcessingException {
        Company company = dtoTransformHelper.transformCompanyDtoToEntity(companyDto);
        Company savedCompany = companyRepository.save(company);
        System.out.println("Company Ingested: " + savedCompany.getName());
        NotificationMessage notificationMessage = new NotificationMessage(String.valueOf(savedCompany.getId()),
                "Company Ingested");
        eventNotificationService.notifyIngestionCompletion(notificationMessage);
    }

}
