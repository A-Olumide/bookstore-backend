package com.bs.bookStore.service;

import com.bs.bookStore.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    Page<BookDto> getAllBooks(int pageNo, int pageSize);

   Page<BookDto> searchBooks(String title, String author, String genre, Integer year, Pageable pageable);


}
