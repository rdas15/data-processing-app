package com.velotio.dataprocessor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    private String suite;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
