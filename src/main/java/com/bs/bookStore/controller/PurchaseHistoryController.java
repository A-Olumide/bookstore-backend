package com.bs.bookStore.controller;

import com.bs.bookStore.constants.StoreConstants;
import com.bs.bookStore.dto.PurchaseHistoryDto;
import com.bs.bookStore.dto.ResponseDto;
import com.bs.bookStore.service.PurchaseHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase-history")
public class PurchaseHistoryController {

    private PurchaseHistoryService purchaseHistoryService;

    public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }


    @GetMapping("/view")
    public ResponseEntity<ResponseDto<Page<PurchaseHistoryDto>>> viewPurchaseHistory(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Page<PurchaseHistoryDto> purchaseHistoryPage = purchaseHistoryService.getAllPurchases(pageNo, pageSize);
        ResponseDto<Page<PurchaseHistoryDto>> response = new ResponseDto<>(
                HttpStatus.OK,
                StoreConstants.STATUS_200,
                StoreConstants.MESSAGE_200,
                purchaseHistoryPage
        );
        return ResponseEntity.ok(response);
    }
}
