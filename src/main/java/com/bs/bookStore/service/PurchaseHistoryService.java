package com.bs.bookStore.service;

import com.bs.bookStore.entity.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryService {
    List<PurchaseHistory> getAllPurchases();
}
