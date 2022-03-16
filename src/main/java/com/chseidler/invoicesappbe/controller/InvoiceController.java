package com.chseidler.invoicesappbe.controller;

import com.chseidler.invoicesappbe.dto.InvoiceDTO;
import com.chseidler.invoicesappbe.exception.UserServiceException;
import com.chseidler.invoicesappbe.model.request.InvoiceDetailsRequestModel;
import com.chseidler.invoicesappbe.model.ui.InvoiceRest;
import com.chseidler.invoicesappbe.service.InvoiceService;
import com.chseidler.invoicesappbe.utils.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public InvoiceRest createInvoice(@RequestBody InvoiceDetailsRequestModel invoiceDetails) throws Exception {

        if (invoiceDetails.getInvoiceDate().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }
        if (invoiceDetails.getInvoiceName().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }
        if (invoiceDetails.getInvoiceValue().isNaN()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }
        if (invoiceDetails.getInvoiceStatus().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        InvoiceRest invoiceRest = new InvoiceRest();

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        BeanUtils.copyProperties(invoiceDetails, invoiceDTO);

        InvoiceDTO createdInvoiceDTO = invoiceService.createInvoice(invoiceDTO);
        BeanUtils.copyProperties(createdInvoiceDTO, invoiceRest);

        return invoiceRest;
    }

    @GetMapping(path = "/{id}")
    public InvoiceRest getInvoice(@PathVariable("id") String invoiceId) {

        InvoiceRest invoiceRest = new InvoiceRest();
        InvoiceDTO invoiceDTO = invoiceService.getInvoiceByInvoiceId(invoiceId);
        BeanUtils.copyProperties(invoiceDTO, invoiceRest);

        return invoiceRest;
    }

    @PutMapping(path = "/{id}")
    public InvoiceRest updateInvoice(@PathVariable("id") String invoiceId
            , @RequestBody InvoiceDetailsRequestModel invoiceDetails) throws Exception {

        if (invoiceDetails.getInvoiceDate().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }
        if (invoiceDetails.getInvoiceName().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }
        if (invoiceDetails.getInvoiceValue().isNaN()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }
        if (invoiceDetails.getInvoiceStatus().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        InvoiceRest invoiceRest = new InvoiceRest();

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        BeanUtils.copyProperties(invoiceDetails, invoiceDTO);

        InvoiceDTO updatedInvoiceDTO = invoiceService.updateInvoice(invoiceId, invoiceDTO);
        BeanUtils.copyProperties(updatedInvoiceDTO, invoiceRest);

        return invoiceRest;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteInvoice(@PathVariable("id") String invoiceId) {

        invoiceService.deleteInvoice(invoiceId);
    }

    @GetMapping
    public List<InvoiceRest> getInvoices(@RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "limit", defaultValue = "20") int limit) {
        List<InvoiceRest> invoiceRestList = new ArrayList<>();

        List<InvoiceDTO> invoiceDTOList = invoiceService.getInvoices(page, limit);

        for (InvoiceDTO invoiceDTO : invoiceDTOList) {
            InvoiceRest invoiceRest = new InvoiceRest();
            BeanUtils.copyProperties(invoiceDTO, invoiceRest);
            invoiceRestList.add(invoiceRest);
        }

        return invoiceRestList;
    }
}
