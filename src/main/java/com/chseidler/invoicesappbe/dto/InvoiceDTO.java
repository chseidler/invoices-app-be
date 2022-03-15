package com.chseidler.invoicesappbe.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvoiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String userId;
    private String invoiceId;
    private String invoiceName;
    private Date invoiceDate;
    private BigDecimal invoiceValue;
    private String invoiceStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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
