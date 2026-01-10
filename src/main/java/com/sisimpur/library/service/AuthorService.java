package com.sisimpur.library.service;

import com.sisimpur.library.dto.authors.AuthorCreateDto;
import com.sisimpur.library.dto.authors.AuthorGetDto;
import com.sisimpur.library.dto.authors.AuthorUpdateDto;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.repository.AuthorRepository;
import com.sisimpur.library.repository.LibraryUserRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AuthorService {
    Author getById(Long id);
    AuthorGetDto create(AuthorCreateDto createDto);
    void delete(Long id);
    void update(Long id, AuthorUpdateDto updateDto);
    AuthorGetDto getAuthorById(Long id);
    Page<AuthorRepository.AuthorExt> getAllAuthors(Optional<Integer> page, Optional<Integer> size);
}
