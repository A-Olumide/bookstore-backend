package com.bs.bookStore.controller;

import com.bs.bookStore.constants.StoreConstants;
import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.dto.ResponseDto;
import com.bs.bookStore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        bookService.addBook(bookDto);
        String successMessage = StoreConstants.getBookAddedMessage(bookDto.getTitle());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StoreConstants.STATUS_201, successMessage));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/authors")
    public ResponseEntity<List<BookDto>> searchBooksByAuthor(@RequestParam String author){
      List<BookDto> books = bookService.searchBooksByAuthor(author);
      return ResponseEntity.ok(books);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookDto>> searchBookByTitle(@RequestParam String title){
        List<BookDto> books = bookService.searchBookByTitle(title);
        return ResponseEntity.ok(books);
    }


}
