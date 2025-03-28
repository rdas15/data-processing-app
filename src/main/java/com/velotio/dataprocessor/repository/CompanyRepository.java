package com.velotio.dataprocessor.repository;

import com.velotio.dataprocessor.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String companyName);
}
