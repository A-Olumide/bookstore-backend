package com.bs.bookStore.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long cartItemId;
     private String bookTitle;
    private int quantity;

}
