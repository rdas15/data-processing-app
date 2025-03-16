package com.velotio.dataprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedProduct {

    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Double)
    private Double price;
}
