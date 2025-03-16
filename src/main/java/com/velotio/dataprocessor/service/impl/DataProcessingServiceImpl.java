package com.velotio.dataprocessor.service.impl;

import com.velotio.dataprocessor.model.NotificationMessage;
import com.velotio.dataprocessor.model.ProcessedAddress;
import com.velotio.dataprocessor.model.ProcessedCompany;
import com.velotio.dataprocessor.model.ProcessedProduct;
import com.velotio.dataprocessor.service.DataProcessingService;
import com.velotio.dataprocessor.service.EventNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.repository.CompanyRepository;
import com.velotio.dataprocessor.service.DataIndexingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DataIndexingService dataIndexingService;

    @Autowired
    private EventNotificationService eventNotificationService;

    @Override
    public void processRawData(NotificationMessage message) {
        // Process the raw data
        Company company = companyRepository.findById(
                Long.valueOf(message.getCompanyId())).orElse(null);
        System.out.println("Processing data for company: " + company);
        if (company != null) {
            System.out.println("Company Name: " + company.getName());
            List<ProcessedAddress> processedAddresses = company.getAddresses().stream().map(address -> {
                String oneLineAddress = address.getSuite() + ", " + address.getStreet() +
                        ", " + address.getCity() + ", " + address.getState() +
                        ", " + address.getCountry() + ", " + address.getZipCode();
                System.out.println("Address: " + oneLineAddress);
                ProcessedAddress processedAddress = new ProcessedAddress();
                processedAddress.setAddressLineItem(oneLineAddress);
                return processedAddress;
            }).collect(Collectors.toList());

            List<ProcessedProduct> processedProducts = company.getProducts().stream().map(product -> {
                String id = company.getShortName() + "-" + product.getId();
                ProcessedProduct processedProduct = new ProcessedProduct();
                processedProduct.setId(id);
                processedProduct.setName(product.getName());
                processedProduct.setPrice(product.getPrice());
                processedProduct.setDescription(product.getDescription());
                return processedProduct;
            }).collect(Collectors.toList());

            ProcessedCompany processedCompany = new ProcessedCompany();
            processedCompany.setName(company.getName());
            processedCompany.setShortName(company.getShortName());
            processedCompany.setAddresses(processedAddresses);
            processedCompany.setProducts(processedProducts);
            processedCompany.setIncorporationDate(company.getIncorporationDate());
            System.out.println("Processed Company: " + processedCompany);

            dataIndexingService.indexData(processedCompany);
            NotificationMessage notificationMessage = new NotificationMessage(String.valueOf(company.getId()),
                    "Company Processed and Indexed");
            eventNotificationService.notifyProcessingCompletion(notificationMessage);
        }
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}
