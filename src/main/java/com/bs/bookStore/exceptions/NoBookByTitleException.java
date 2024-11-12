package com.bs.bookStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoBookByTitleException extends RuntimeException{
    public NoBookByTitleException (String title) {
        super("No books found with the title: " + title);
    }
}
