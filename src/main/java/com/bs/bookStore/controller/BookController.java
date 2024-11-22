package com.bs.bookStore.controller;

import com.bs.bookStore.constants.StoreConstants;
import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.dto.ResponseDto;
import com.bs.bookStore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/book/")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookDto bookDto) {
        BookDto book = bookService.addBook(bookDto);
        ResponseDto<BookDto> responseDto = new ResponseDto<>(
                HttpStatus.CREATED,
                StoreConstants.STATUS_201,
                StoreConstants.MESSAGE_201,
                book
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Page<BookDto>>> findAllBooks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Page<BookDto> books = bookService.getAllBooks(pageNo, pageSize);
        ResponseDto<Page<BookDto>> response = new ResponseDto<>(
                HttpStatus.OK,
                StoreConstants.STATUS_200,
                StoreConstants.MESSAGE_200,
                books
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<Page<BookDto>>> searchBooks(@Valid
            @RequestParam(required = false)  String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<BookDto> booksPage = bookService.searchBooks(title, author, genre, year, pageable);
        ResponseDto<Page<BookDto>> responseDto = new ResponseDto<>(
                HttpStatus.OK,
                StoreConstants.STATUS_200,
                StoreConstants.MESSAGE_200,
                booksPage
        );
        return ResponseEntity.ok(responseDto);
    }

}
