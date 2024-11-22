package com.bs.bookStore.controller;

import com.bs.bookStore.constants.StoreConstants;
import com.bs.bookStore.dto.CartItemCreationDto;
import com.bs.bookStore.dto.CartItemDto;
import com.bs.bookStore.dto.ResponseDto;
import com.bs.bookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    public ResponseEntity<ResponseDto> addBookToCart(@RequestBody CartItemCreationDto cartItemCreationDto){
       CartItemDto cartItemDto = cartService.addBookToCart(cartItemCreationDto);
        ResponseDto<CartItemDto> responseDto = new ResponseDto<>(
                HttpStatus.CREATED,
                StoreConstants.STATUS_201,
                StoreConstants.MESSAGE_201,
                cartItemDto
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("view")
    public ResponseEntity<ResponseDto<List<CartItemDto>>> viewCart(){
        List<CartItemDto> items = cartService.viewCart();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HttpStatus.OK, StoreConstants.STATUS_200, StoreConstants.MESSAGE_200, items));
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<ResponseDto> removeBookFromCart(@PathVariable Long cartItemId) {
        cartService.removeBookFromCart(cartItemId);
        ResponseDto<Void> responseDto = new ResponseDto<>(
                HttpStatus.OK,
                StoreConstants.STATUS_200,
                StoreConstants.MESSAGE_200,
                null
        );
        return ResponseEntity.ok(responseDto);
    }
}
