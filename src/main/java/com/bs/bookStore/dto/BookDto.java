package com.bs.bookStore.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookDto {
    @NotEmpty(message = "Title must not be null or empty")
    private String title;

    @NotEmpty(message = "Author must not be null or empty")
    private String author;

    @NotEmpty(message = "Genre must not be null or empty")
    private String genre;

    @NotEmpty(message = "Isbn must not be null or empty")
    private String isbn;

    @Min(value = 1000, message = "Publication year must be a valid year")
    @Max(value = 2024, message = "Publication year cannot be in the future")
    private int publicationYear;
}
