package com.bs.bookStore.utils;

import com.bs.bookStore.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                author == null || author.isEmpty()
                        ? criteriaBuilder.isTrue(criteriaBuilder.literal(true))
                        : criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }


    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                (title == null || title.trim().isEmpty()) ? null :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.trim().toLowerCase() + "%");
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, criteriaBuilder) ->
                (genre == null || genre.trim().isEmpty()) ? null :
                        criteriaBuilder.equal(criteriaBuilder.lower(root.get("genre")), genre.trim().toLowerCase());
    }

    public static Specification<Book> hasYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                (year == null) ? null : criteriaBuilder.equal(root.get("publicationYear"), year);
    }


}
