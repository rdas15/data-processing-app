package com.velotio.dataprocessor.service;

import com.velotio.dataprocessor.model.entity.Company;
import com.velotio.dataprocessor.model.NotificationMessage;

public interface DataProcessingService {

    void processRawData(NotificationMessage company);

    Company getCompanyById(Long companyId);
}
