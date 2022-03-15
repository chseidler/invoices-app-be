package com.chseidler.invoicesappbe.model.request;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceDetailsRequestModel {

    private String userId;
    private String invoiceName;
    private Date invoiceDate;
    private BigDecimal invoiceValue;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(BigDecimal invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
