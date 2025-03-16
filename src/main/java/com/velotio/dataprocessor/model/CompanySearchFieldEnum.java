package com.velotio.dataprocessor.model;

public enum CompanySearchFieldEnum {

    COMPANY_NAME("companyName"), COMPANY_SHORT_NAME("companyShortName"),
    PRODUCT_NAME("productName"), PRODUCT_DESCRIPTION("productDescription"),
    ADDRESS_LINE_ITEM("addressLineItem");

    private String field;

    CompanySearchFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
