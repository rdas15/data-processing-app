package com.velotio.dataprocessor.model;


public class NotificationMessage {

    private String companyId;
    private String statusMessage;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public NotificationMessage() {
    }

    public NotificationMessage(String companyId, String statusMessage) {
        this.companyId = companyId;
        this.statusMessage = statusMessage;
    }
}
