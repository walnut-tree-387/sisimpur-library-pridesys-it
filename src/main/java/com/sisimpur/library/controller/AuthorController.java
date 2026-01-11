package com.sisimpur.library.controller;

import com.sisimpur.library.dto.authors.AuthorCreateDto;
import com.sisimpur.library.dto.authors.AuthorGetDto;
import com.sisimpur.library.dto.authors.AuthorUpdateDto;
import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.service.AuthorService;
import com.sisimpur.library.service.BookService;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }
    @PostMapping()
    public ResponseEntity<AuthorGetDto> create(@RequestBody AuthorCreateDto createDto){
        AuthorGetDto response = authorService.create(createDto);
        if(!createDto.getAuthorBooks().isEmpty()){              // Creating the books after ensuring author of these books is created
            for(BookCreateDto bookCreateDto : createDto.getAuthorBooks()) {
                bookCreateDto.setAuthorId(response.getId());
                bookService.create(bookCreateDto);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorGetDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AuthorUpdateDto updateDto){
        authorService.update(id, updateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        authorService.delete(id);
        // After Deleting the author - flagging his books also as deleted
        bookService.deleteAuthorBooks(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/all")
    public PagedModel<?> getAllAuthors(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        return new PagedModel<>(authorService.getAllAuthors(page, size));
    }
}
