package com.bs.bookStore.dto;

import com.bs.bookStore.entity.Book;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseHistoryDto {

    private Long purchaseId;

    private Book book;

    private int quantity;

    private LocalDateTime purchaseDate;
}
