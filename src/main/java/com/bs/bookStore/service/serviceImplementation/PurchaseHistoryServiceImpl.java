package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.entity.PurchaseHistory;
import com.bs.bookStore.repository.PurchaseHistoryRepository;
import com.bs.bookStore.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;

    public PurchaseHistoryServiceImpl(PurchaseHistoryRepository purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Override
    public List<PurchaseHistory> getAllPurchases() {
        return purchaseHistoryRepository.findAll();
    }
}
