package com.bs.bookStore.service;

import com.bs.bookStore.dto.PurchaseHistoryDto;
import org.springframework.data.domain.Page;

public interface PurchaseHistoryService {
    Page<PurchaseHistoryDto> getAllPurchases(int pageNo, int pageSize);
}
