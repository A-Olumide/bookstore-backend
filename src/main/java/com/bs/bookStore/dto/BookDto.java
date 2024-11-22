package com.bs.bookStore.dto;

import com.bs.bookStore.exceptions.InvalidIsbnException;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.commons.validator.routines.ISBNValidator;

@Data
public class BookDto {
    private Long id;

    @NotBlank(message = "Please enter the Book Title")
    private String title;

    @NotBlank(message = "Please enter Author name")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Author name can only contain letters and spaces")
    private String author;

    @NotEmpty(message = "This field must not be empty")
    private String genre;

    @NotEmpty(message = "This field must not empty")
    private String isbn;

    @Min(value = 1000, message = "Publication year must be a valid year")
    @Max(value = 2024, message = "Publication year cannot be in the future")
    private int publicationYear;

        public void setIsbn(String isbn) {
            if (isbn == null || isbn.isEmpty() || !org.apache.commons.validator.routines.ISBNValidator.getInstance().isValid(isbn)) {
                throw new InvalidIsbnException("Invalid ISBN format: " + isbn);
            }
            this.isbn = isbn;
        }

    }

