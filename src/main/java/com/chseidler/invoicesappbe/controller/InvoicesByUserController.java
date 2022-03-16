package com.chseidler.invoicesappbe.controller;

import com.chseidler.invoicesappbe.dto.InvoiceDTO;
import com.chseidler.invoicesappbe.model.ui.InvoiceRest;
import com.chseidler.invoicesappbe.service.InvoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class InvoicesByUserController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(path = "/{id}")
    public List<InvoiceRest> getInvoicesByUserId(@PathVariable("id") String userId) {

        List<InvoiceRest> invoiceRestList = new ArrayList<>();

        List<InvoiceDTO> invoiceDTOList = invoiceService.getInvoicesByUserId(userId);

        for (InvoiceDTO invoiceDTO : invoiceDTOList) {
            InvoiceRest invoiceRest = new InvoiceRest();
            BeanUtils.copyProperties(invoiceDTO, invoiceRest);
            invoiceRestList.add(invoiceRest);
        }

        return invoiceRestList;
    }
}
