package com.chseidler.invoicesappbe.repository;

import com.chseidler.invoicesappbe.entity.InvoiceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<InvoiceEntity, Long> {

    InvoiceEntity findByUserId(String userId);

    InvoiceEntity findByInvoiceId(String invoiceId);
}
