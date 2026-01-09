package com.sisimpur.library.controller;

import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.dto.books.BookGetDto;
import com.sisimpur.library.dto.books.BookUpdateDto;
import com.sisimpur.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sisimpur.library.model.Book;
import com.sisimpur.library.service.BookServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookGetDto> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<BookGetDto> createBook(@RequestBody BookCreateDto createBookDto) {
        return new ResponseEntity<>(bookService.create(createBookDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookGetDto> updateBook(@PathVariable Long id, @RequestBody BookUpdateDto updateDto){
        bookService.updateBookById(id, updateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
