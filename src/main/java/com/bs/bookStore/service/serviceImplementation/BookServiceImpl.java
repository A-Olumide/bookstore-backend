package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.entity.Book;
import com.bs.bookStore.exceptions.BookAlreadyExistsException;
import com.bs.bookStore.exceptions.ResourceNotFoundException;
import com.bs.bookStore.repository.BookRepository;
import com.bs.bookStore.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;


    @Override
    public BookDto addBook(BookDto bookDto) {
        if (bookRepository.existsByTitleIgnoreCase(bookDto.getTitle())) {
            throw new BookAlreadyExistsException("Book with title '" + bookDto.getTitle() + "' already exists");
        }
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
        return modelMapper.map(book, BookDto.class);
    }
    @Override
    public List<BookDto> getAllBooks() {
       List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<BookDto> searchBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        if(books.isEmpty()){
            throw new ResourceNotFoundException("Book", "Author", author);
        }
        return books.stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> searchBookByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        if(books.isEmpty()){
            throw new ResourceNotFoundException("Book", "Title", title);
        }
        return books.stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> searchBooksByGenre(String genre) {
       List<Book> books = bookRepository.findByGenreIgnoreCase(genre);
       if(books.isEmpty()){
           throw new ResourceNotFoundException("Book", "Genre", genre);
       }
        return books.stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> searchBooksByYear(int year) {
       List<Book> books = bookRepository.findByPublicationYear(year);
       if(books.isEmpty()){
           throw new ResourceNotFoundException("Book", "Year", String.valueOf(year));
       }
        return books.stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }
}
