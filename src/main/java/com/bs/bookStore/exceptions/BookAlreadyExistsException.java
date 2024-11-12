package com.bs.bookStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String message){super(message);}

}
