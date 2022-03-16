package com.chseidler.invoicesappbe.service.impl;

import com.chseidler.invoicesappbe.dto.InvoiceDTO;
import com.chseidler.invoicesappbe.entity.InvoiceEntity;
import com.chseidler.invoicesappbe.exception.UserServiceException;
import com.chseidler.invoicesappbe.repository.InvoiceRepository;
import com.chseidler.invoicesappbe.service.InvoiceService;
import com.chseidler.invoicesappbe.utils.ErrorMessages;
import com.chseidler.invoicesappbe.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    Utils utils;

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        InvoiceEntity invoiceEntityByInvoiceId = invoiceRepository.findByInvoiceId(invoiceDTO.getInvoiceId());
        if (invoiceEntityByInvoiceId != null) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        BeanUtils.copyProperties(invoiceDTO, invoiceEntity);

        String publicInvoiceId = utils.generateUserId(10).toUpperCase();
        invoiceEntity.setInvoiceId(publicInvoiceId);

        InvoiceEntity storedInvoiceEntity = invoiceRepository.save(invoiceEntity);

        InvoiceDTO returnInvoiceDTO = new InvoiceDTO();
        BeanUtils.copyProperties(storedInvoiceEntity, returnInvoiceDTO);

        return returnInvoiceDTO;
    }

    @Override
    public InvoiceDTO getInvoiceByInvoiceId(String invoiceId) {

        InvoiceDTO returnInvoiceDTO = new InvoiceDTO();
        InvoiceEntity invoiceEntityByInvoiceId = invoiceRepository.findByInvoiceId(invoiceId);

        if (invoiceEntityByInvoiceId == null) {
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        }

        BeanUtils.copyProperties(invoiceEntityByInvoiceId, returnInvoiceDTO);

        return returnInvoiceDTO;
    }

    @Override
    public InvoiceDTO updateInvoice(String invoiceId, InvoiceDTO invoiceDTO) {

        InvoiceDTO returnInvoiceDTO = new InvoiceDTO();
        InvoiceEntity invoiceEntityByInvoiceId = invoiceRepository.findByInvoiceId(invoiceId);

        if (invoiceEntityByInvoiceId == null) {
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        }

        invoiceEntityByInvoiceId.setInvoiceDate(invoiceDTO.getInvoiceDate());
        invoiceEntityByInvoiceId.setInvoiceName(invoiceDTO.getInvoiceName());
        invoiceEntityByInvoiceId.setInvoiceValue(invoiceDTO.getInvoiceValue());
        invoiceEntityByInvoiceId.setInvoiceStatus(invoiceDTO.getInvoiceStatus());

        InvoiceEntity updatedInvoiceEntity = invoiceRepository.save(invoiceEntityByInvoiceId);

        BeanUtils.copyProperties(updatedInvoiceEntity, returnInvoiceDTO);
        return returnInvoiceDTO;
    }

    @Override
    public void deleteInvoice(String invoiceId) {

        InvoiceEntity invoiceEntityByInvoiceId = invoiceRepository.findByInvoiceId(invoiceId);

        if (invoiceEntityByInvoiceId == null) {
            throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        }

        invoiceRepository.delete(invoiceEntityByInvoiceId);
    }

    @Override
    public List<InvoiceDTO> getInvoices(int page, int limit) {

        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();

        if (page > 0) { page -= 1;}

        Pageable pageable = PageRequest.of(page, limit);
        Page<InvoiceEntity> invoicesPage = invoiceRepository.findAll(pageable);

        List<InvoiceEntity> invoiceEntityList = invoicesPage.getContent();

        for (InvoiceEntity invoiceEntity : invoiceEntityList) {
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            BeanUtils.copyProperties(invoiceEntity, invoiceDTO);
            invoiceDTOList.add(invoiceDTO);
        }

        return invoiceDTOList;
    }

    @Override
    public List<InvoiceDTO> getInvoicesByUserId(String userId) {

        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();

        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findByUserId(userId);

        for (InvoiceEntity invoiceEntity : invoiceEntityList) {
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            BeanUtils.copyProperties(invoiceEntity, invoiceDTO);
            invoiceDTOList.add(invoiceDTO);
        }

        return invoiceDTOList;
    }
}
