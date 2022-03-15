package com.chseidler.invoicesappbe.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "invoices")
public class InvoiceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String userId;
    private String invoiceId;
    private Date invoiceDate;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal invoiceValue;


    private String invoiceStatus;
}
