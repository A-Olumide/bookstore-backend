package com.bs.bookStore.exceptions;

import com.bs.bookStore.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(InvalidIsbnException.class)
    public ResponseEntity<Map<String, String>> handleInvalidIsbnException(InvalidIsbnException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookstoreException.class)
    public ResponseEntity<Object> handleBookstoreException(BookstoreException ex) {
        return new ResponseEntity<>("Exception", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
//
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                null
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleBookAlreadyExistsException(BookAlreadyExistsException exception){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                null
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartEmptyException.class)
    public ResponseEntity<ErrorResponseDto> handleCartEmptyException(CartEmptyException exception){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                null
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
