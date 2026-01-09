package com.sisimpur.library.controller;

import com.sisimpur.library.dto.authors.AuthorCreateDto;
import com.sisimpur.library.dto.authors.AuthorGetDto;
import com.sisimpur.library.dto.authors.AuthorUpdateDto;
import com.sisimpur.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping()
    public ResponseEntity<AuthorGetDto> create(@RequestBody AuthorCreateDto createDto){
        return new ResponseEntity<>(authorService.create(createDto), HttpStatus.CREATED);
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
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
