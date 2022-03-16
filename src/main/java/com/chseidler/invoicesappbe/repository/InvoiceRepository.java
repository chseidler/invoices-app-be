package com.chseidler.invoicesappbe.repository;

import com.chseidler.invoicesappbe.entity.InvoiceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findByUserId(String userId);

    InvoiceEntity findByInvoiceId(String invoiceId);
}
