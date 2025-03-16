package com.velotio.dataprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "company-index")
public class ProcessedCompany {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String shortName;

    @Field(type = FieldType.Date)
    private Date incorporationDate;

    @Field(type = FieldType.Nested)
    private List<ProcessedAddress> addresses;

    @Field(type = FieldType.Nested)
    private List<ProcessedProduct> products;

}
