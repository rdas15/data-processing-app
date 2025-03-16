package com.velotio.dataprocessor.util;

import com.velotio.dataprocessor.model.AddressDto;
import com.velotio.dataprocessor.model.CompanyDto;
import com.velotio.dataprocessor.model.ProductDto;
import com.velotio.dataprocessor.model.entity.Address;
import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.model.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoTransformHelper {

    public Company transformCompanyDtoToEntity(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setShortName(companyDto.getShortName());
        company.setIncorporationDate(companyDto.getIncorporationDate());
        company.setAddresses(transformAddressDtoToEntity(companyDto.getAddresses(), company));
        company.setProducts(transformProductDtoToEntity(companyDto.getProducts(), company));

        return company;
    }

    private List<Address> transformAddressDtoToEntity(List<AddressDto> addressDtos, Company company){
        List<Address> addresses = new ArrayList<>();
        for(AddressDto addressDto : addressDtos) {
            Address address = new Address();
            address.setSuite(addressDto.getSuite());
            address.setStreet(addressDto.getStreet());
            address.setCity(addressDto.getCity());
            address.setState(addressDto.getState());
            address.setCountry(addressDto.getCountry());
            address.setZipCode(addressDto.getZipCode());
            address.setCompany(company);
            addresses.add(address);
        }

        return addresses;
    }

    private List<Product> transformProductDtoToEntity(List<ProductDto> productDtos, Company company){
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtos) {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCompany(company);
            products.add(product);
        }
        return products;
    }
}
