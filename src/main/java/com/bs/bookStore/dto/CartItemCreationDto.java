package com.bs.bookStore.dto;

import lombok.Data;

@Data
public class CartItemCreationDto{
    private String bookTitle;
    private int quantity;
}
