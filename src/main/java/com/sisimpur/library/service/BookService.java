package com.sisimpur.library.service;

import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.dto.books.BookGetDto;
import com.sisimpur.library.dto.books.BookUpdateDto;
import com.sisimpur.library.model.Book;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;

public interface BookService {
    BookGetDto create(BookCreateDto createDto);
    Book getById(Long id);    // For Other service
    BookGetDto getBookById(Long id); // For Controller
    void deleteBookById(Long id);
    void update(Long id, BookUpdateDto bookUpdateDto);
    Page<BookGetDto> searchBooks(Map<String, Object> searchParams,
                                 Optional<Integer> page, Optional<Integer> size);
    void changeAvailabilityStatusById(Long id);
}
