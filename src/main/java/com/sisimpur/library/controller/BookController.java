package com.sisimpur.library.controller;

import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.dto.books.BookGetDto;
import com.sisimpur.library.dto.books.BookUpdateDto;
import com.sisimpur.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookGetDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<BookGetDto> create(@Valid @RequestBody BookCreateDto createBookDto) {
        return new ResponseEntity<>(bookService.create(createBookDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookGetDto> update(@PathVariable Long id, @RequestBody BookUpdateDto updateDto){
        bookService.update(id, updateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/search")
    public PagedModel<?> searchBooks(@RequestBody Map<String, Object> searchParams,
                                     @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        return new PagedModel<>(bookService.searchBooks(searchParams, page, size));
    }
}
