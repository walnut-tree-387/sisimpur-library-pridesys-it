package com.sisimpur.library.controller;

import com.sisimpur.library.dto.authors.AuthorCreateDto;
import com.sisimpur.library.dto.authors.AuthorGetDto;
import com.sisimpur.library.dto.authors.AuthorUpdateDto;
import com.sisimpur.library.dto.users.LibraryUserCreateDto;
import com.sisimpur.library.dto.users.LibraryUserGetDto;
import com.sisimpur.library.dto.users.LibraryUserUpdateDto;
import com.sisimpur.library.service.LibraryUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final LibraryUserService libraryUserService;

    public UserController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @PostMapping()
    public ResponseEntity<LibraryUserGetDto> create(@RequestBody LibraryUserCreateDto createDto){
        return new ResponseEntity<>(libraryUserService.create(createDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibraryUserGetDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(libraryUserService.getUserById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody LibraryUserUpdateDto updateDto){
        libraryUserService.update(id, updateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        libraryUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
