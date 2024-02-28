package me.kzv.opensearch.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import me.kzv.opensearch.document.Book;
import me.kzv.opensearch.validator.PublicationYear;

@Data
public class BookDto {

    @NotBlank
    private String title;

    @Positive
    @PublicationYear
    private Integer publicationYear;

    @NotBlank
    private String authorName;

    @NotBlank
    private String isbn;

    public static Book from(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.title);
        book.setPublicationYear(bookDto.publicationYear);
        book.setAuthorName(bookDto.authorName);
        book.setIsbn(bookDto.isbn);
        return book;
    }
}
