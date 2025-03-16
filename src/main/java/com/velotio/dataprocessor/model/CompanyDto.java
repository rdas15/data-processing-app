package com.velotio.dataprocessor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDto {

    private String shortName;
    private String name;
    private Date incorporationDate;
    private List<AddressDto> addresses;
    private List<ProductDto> products;
}
