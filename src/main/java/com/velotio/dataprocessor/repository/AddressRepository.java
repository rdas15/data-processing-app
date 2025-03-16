package com.velotio.dataprocessor.repository;

import com.velotio.dataprocessor.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
