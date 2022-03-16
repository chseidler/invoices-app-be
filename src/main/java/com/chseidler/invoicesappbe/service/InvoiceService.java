package com.chseidler.invoicesappbe.service;

import com.chseidler.invoicesappbe.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO getInvoiceByInvoiceId(String invoiceId);
    InvoiceDTO updateInvoice(String invoiceId, InvoiceDTO invoiceDTO);
    void deleteInvoice(String invoiceId);
    List<InvoiceDTO> getInvoices(int page, int limit);
    List<InvoiceDTO> getInvoicesByUserId(String userId);
}
