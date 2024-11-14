package com.bs.bookStore.controller;

import com.bs.bookStore.constants.StoreConstants;
import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.dto.CartItemCreationDto;
import com.bs.bookStore.dto.CartItemDto;
import com.bs.bookStore.dto.ResponseDto;
import com.bs.bookStore.service.CartService;
import jakarta.validation.Valid;
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
        cartService.addBookToCart(cartItemCreationDto);
        String successMessage = StoreConstants.getBookAddedMessage(cartItemCreationDto.getBookTitle());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StoreConstants.STATUS_201, successMessage));
    }

    @GetMapping("view")
    public ResponseEntity<List<CartItemDto>> viewCart(){

        return ResponseEntity.ok(cartService.viewCart());
    }

    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<ResponseDto> removeBookFromCart(@PathVariable Long bookId) {
        cartService.removeBookFromCart(bookId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StoreConstants.STATUS_200, StoreConstants.MESSAGE_200));
    }

}
