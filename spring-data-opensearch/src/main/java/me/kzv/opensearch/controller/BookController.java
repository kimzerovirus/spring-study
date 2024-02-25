package me.kzv.opensearch.controller;


import jakarta.validation.Valid;
import me.kzv.opensearch.controller.dto.BookDto;
import me.kzv.opensearch.document.Book;
import me.kzv.opensearch.exception.BookNotFoundException;
import me.kzv.opensearch.exception.DuplicateIsbnException;
import me.kzv.opensearch.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Book createBook(@Valid @RequestBody BookDto book) throws DuplicateIsbnException {
        return bookService.create(BookDto.from(book));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        return bookService.getByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("The given isbn is invalid"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/query")
    public List<Book> getBooksByAuthorAndTitle(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author) {
        return bookService.findByTitleAndAuthor(title, author);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody BookDto book) throws BookNotFoundException {
        return bookService.update(id, BookDto.from(book));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }
}
