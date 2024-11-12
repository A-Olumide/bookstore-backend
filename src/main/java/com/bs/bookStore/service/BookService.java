package com.bs.bookStore.service;

import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.entity.Book;

import java.util.List;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    List<BookDto> searchBooksByAuthor(String author);

    List<BookDto> searchBookByTitle(String title);
}
