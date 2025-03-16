package com.velotio.dataprocessor.repository.opensearch;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.velotio.dataprocessor.model.ProcessedCompany;

import java.util.List;

@Repository
public interface ProcessedCompanyRepository extends ElasticsearchRepository<ProcessedCompany, Long>{

    List<ProcessedCompany> findByNameContaining(String keyword);

    List<ProcessedCompany> findByShortNameContaining(String keyword);

    @Query("{\"nested\": {\"path\": \"addresses\", \"query\": {\"match\": {\"addresses.addressLineItem\": \"?0\"}}}}")
    List<ProcessedCompany> findByAddressLineItemContaining(String keyword);

    @Query("{\"nested\":{\"path\":\"products\",\"query\":{\"match\":{\"products.name\":\"?0\"}}}}")
    List<ProcessedCompany> findByProductName(String description);

    @Query("{\"nested\":{\"path\":\"products\",\"query\":{\"match\":{\"products.description\":\"?0\"}}}}")
    List<ProcessedCompany> findByProductDescriptionContaining(String description);

}
