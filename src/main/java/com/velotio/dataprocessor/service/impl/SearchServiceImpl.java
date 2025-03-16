package com.velotio.dataprocessor.service.impl;

import com.velotio.dataprocessor.model.*;
import com.velotio.dataprocessor.model.entity.Address;
import com.velotio.dataprocessor.model.entity.Product;
import com.velotio.dataprocessor.repository.opensearch.ProcessedCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.repository.CompanyRepository;
import com.velotio.dataprocessor.service.SearchService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProcessedCompanyRepository processedCompanyRepository;

    public CompanyDto searchCompany(String companyName) {
        Company company = companyRepository.findByName(companyName);
        return transformEntityToCompanyDto(company);
    }

    private CompanyDto transformEntityToCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setShortName(company.getShortName());
        companyDto.setIncorporationDate(company.getIncorporationDate());
        companyDto.setAddresses(transformEntityToAddressDto(company.getAddresses()));
        companyDto.setProducts(transformEntityToProductDto(company.getProducts()));

        return companyDto;
    }

    private List<ProductDto> transformEntityToProductDto(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());

            productDtos.add(productDto);
        }
        return productDtos;
    }

    private List<AddressDto> transformEntityToAddressDto(List<Address> addresses) {
        List<AddressDto> addressDtos = new ArrayList<>();
        for(Address address : addresses) {
            AddressDto addressDto = new AddressDto();
            addressDto.setSuite(address.getSuite());
            addressDto.setStreet(address.getStreet());
            addressDto.setCity(address.getCity());
            addressDto.setState(address.getState());
            addressDto.setCountry(address.getCountry());
            addressDto.setZipCode(address.getZipCode());

            addressDtos.add(addressDto);
        }
        return addressDtos;
    }

    public List<ProcessedCompany> searchByField(CompanySearchFieldEnum field, String value) {
        switch(field) {
            case COMPANY_NAME:
                return processedCompanyRepository.findByNameContaining(value);
            case COMPANY_SHORT_NAME:
                return processedCompanyRepository.findByShortNameContaining(value);
            case PRODUCT_NAME:
                return processedCompanyRepository.findByProductName(value);
            case PRODUCT_DESCRIPTION:
                return processedCompanyRepository.findByProductDescriptionContaining(value);
            case ADDRESS_LINE_ITEM:
                return processedCompanyRepository.findByAddressLineItemContaining(value);
            default:
                return null;
        }
    }
}
