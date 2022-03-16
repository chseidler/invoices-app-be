package com.chseidler.invoicesappbe.model.request;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceDetailsRequestModel {

    private String userId;
    private String invoiceName;
    private String invoiceDate;
    private Double invoiceValue;
    private String invoiceStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(Double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
