package com.bs.bookStore.controller;

import com.bs.bookStore.constants.StoreConstants;
import com.bs.bookStore.dto.ResponseDto;
import com.bs.bookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout/")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{paymentMethod}")
    public ResponseEntity<ResponseDto> checkOut(@PathVariable String paymentMethod) {

        cartService.checkOut();
        switch (paymentMethod.toLowerCase()) {
            case "web":
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto(StoreConstants.STATUS_200, StoreConstants.WEB_MESSAGE));
            case "ussd":
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto(StoreConstants.STATUS_200, StoreConstants.USSD_MESSAGE));
            case "transfer":
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto(StoreConstants.STATUS_200, StoreConstants.TRANSFER_MESSAGE));
            default:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDto(StoreConstants.STATUS_400, StoreConstants.MESSAGE_400));
        }
    }


}
