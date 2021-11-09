package project.Mphasis.Api.bookAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.Mphasis.Api.bookAPI.dto.BookDto;
import project.Mphasis.Api.bookAPI.entity.Book;
import project.Mphasis.Api.bookAPI.service.BookService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
/*
Steps
    1st - Using Book API to fetch Book details and dump it into the database
    2nd - GET request on "/" returning the Book Data from local database
    3rd - GET request on "/bookID" returning individual book details
    4th - GET request on "/search/content" returning all books containing the title content
 */

public class BookController
{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    BookService bookService;
    // this will fetch books from 3rd party API

    @GetMapping
    public List<BookDto> getBook()
    {
       return bookService.getBooks();
    }
    @GetMapping("/search/{content}")
    public List<BookDto> searchBooks(@PathVariable String content)
    {
        return bookService.searchBooks(content);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Integer id)
    {
        return  bookService.getBook(id);
    }

    @PostMapping
    public List<Book> addBooks()
    {
        bookService.fetchBooks();
        return bookService.saveBooks();
    }
}
