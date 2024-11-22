package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.dto.BookDto;
import com.bs.bookStore.entity.Book;
import com.bs.bookStore.exceptions.BookAlreadyExistsException;
import com.bs.bookStore.exceptions.ResourceNotFoundException;
import com.bs.bookStore.repository.BookRepository;
import com.bs.bookStore.service.BookService;
import com.bs.bookStore.utils.BookSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Page<BookDto> getAllBooks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Get the total number of books in the repository
        long totalBooks = bookRepository.count();

        // Calculate the maximum number of pages
        int maxPages = (int) Math.ceil((double) totalBooks / pageSize);

        // Check if the requested page number exceeds the maximum number of pages.
        // If so, adjust the pageable to point to the last available page
        if(pageNo >= maxPages & maxPages > 0){
            pageable = PageRequest.of(maxPages - 1, pageSize);
        }

        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.map(book -> modelMapper.map(book, BookDto.class));
    }

    @Override
    public Page<BookDto> searchBooks(String title, String author, String genre, Integer year, Pageable pageable) {
        Specification<Book> spec = Specification.where(null);

        if (author != null && !author.isEmpty()) {
            spec = spec.and(BookSpecification.hasAuthor(author));
        }
        if (title != null && !title.isEmpty()) {
            spec = spec.and(BookSpecification.hasTitle(title));
        }
        if (genre != null && !genre.isEmpty()) {
            spec = spec.and(BookSpecification.hasGenre(genre));
        }
        if (year != null) {
            spec = spec.and(BookSpecification.hasYear(year));
        }

        boolean anyMatch = bookRepository.exists(spec);
        if(!anyMatch){
            throw new ResourceNotFoundException("Book", "Search Criteria", "No matching books found");
        }

        long totalBooks = bookRepository.count(spec);

        int pageSize = pageable.getPageSize();
        int maxPages = (int) Math.ceil((double) totalBooks / pageSize);

        int requestedPageNo = pageable.getPageNumber();

        if (requestedPageNo >= maxPages) {
            pageable = PageRequest.of(maxPages - 1, pageSize);
        }

        Page<Book> booksPage = bookRepository.findAll(spec, pageable);

        return booksPage.map(book -> modelMapper.map(book, BookDto.class));

    }

}
