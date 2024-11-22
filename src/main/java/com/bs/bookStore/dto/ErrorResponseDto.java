package com.bs.bookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ErrorResponseDto<T> {
    private HttpStatus errorStatus;
    private Integer errorCode;
    private String errorMessage;
    private T data;
}
