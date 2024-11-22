package com.bs.bookStore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CartItemCreationDto{

    @NotBlank(message = "Please enter the Book Title")
    private String bookTitle;

    @Min(value = 1, message = "Book Quantity must be at least 1")
    private int quantity;
}
