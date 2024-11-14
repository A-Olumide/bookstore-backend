package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.entity.Book;
import com.bs.bookStore.exceptions.BookAlreadyExistsException;
import com.bs.bookStore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook_ShouldAddBook_WhenBookDoesNotExist() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Mask");
        bookDto.setAuthor("Author");

        Book book = new Book();
        book.setTitle("Mask");
        when(bookRepository.existsByTitleIgnoreCase(bookDto.getTitle())).thenReturn(false);
        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto result = bookService.addBook(bookDto);

        assertEquals("Mask", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void addBook_ShouldThrowException_WhenBookAlreadyExists() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Existing Book");

        when(bookRepository.existsByTitleIgnoreCase(bookDto.getTitle())).thenReturn(true);

        assertThrows(BookAlreadyExistsException.class, () -> {
            bookService.addBook(bookDto);
        });

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        List<Book> books = Arrays.asList(new Book(), new Book());
        when(bookRepository.findAll()).thenReturn(books);

        Arrays.asList(new BookDto(), new BookDto());
        when(modelMapper.map(any(Book.class), eq(BookDto.class))).thenReturn(new BookDto());

        List<BookDto> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void searchBooksByAuthor_ShouldReturnBooks_WhenAuthorExists() {
        String author = "Mide";

        List<Book> books = Arrays.asList(new Book(), new Book());

        when(bookRepository.findByAuthorContainingIgnoreCase(author)).thenReturn(books);

        when(modelMapper.map(any(Book.class), eq(BookDto.class))).thenReturn(new BookDto());

        List<BookDto> result = bookService.searchBooksByAuthor(author);

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findByAuthorContainingIgnoreCase(author);
    }

}