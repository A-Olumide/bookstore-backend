package com.bs.bookStore.controller;

import com.bs.bookStore.entity.PurchaseHistory;
import com.bs.bookStore.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-history")
public class PurchaseHistoryController {

    private PurchaseHistoryService purchaseHistoryService;

    public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }


    @GetMapping("/view")
    public ResponseEntity<List<PurchaseHistory>> viewPurchaseHistory() {
        return ResponseEntity.ok(purchaseHistoryService.getAllPurchases());
    }
}
