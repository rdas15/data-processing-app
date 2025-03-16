package com.velotio.dataprocessor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private Double price;
}
