package com.bs.bookStore.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDto<T> {
    private HttpStatus status;
    private String statusCode;
    private String statusMsg;
    private T data;


    public ResponseDto(HttpStatus status,String statusCode, String statusMsg, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
    }

    public ResponseDto(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.data = data;
    }
}
