package com.bs.bookStore.service;

import com.bs.bookStore.dto.CartItemCreationDto;
import com.bs.bookStore.dto.CartItemDto;

import java.util.List;

public interface CartService {

    CartItemDto addBookToCart(CartItemCreationDto cartItemCreationDto);
    List<CartItemDto> viewCart();
    void removeBookFromCart(Long cartItemId);
    void checkOut();
}
