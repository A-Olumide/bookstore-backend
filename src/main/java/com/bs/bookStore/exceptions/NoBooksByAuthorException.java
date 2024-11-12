package com.bs.bookStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoBooksByAuthorException extends RuntimeException{
    public NoBooksByAuthorException (String author) {
        super("No books found by author: " + author);
    }
}
