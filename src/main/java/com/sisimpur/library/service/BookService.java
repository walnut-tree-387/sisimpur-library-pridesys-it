package com.sisimpur.library.service;

import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.dto.books.BookGetDto;
import com.sisimpur.library.dto.books.BookUpdateDto;
import com.sisimpur.library.model.Book;

public interface BookService {
    BookGetDto create(BookCreateDto createDto);
    Book getById(Long id);    // For Other service
    BookGetDto getBookById(Long id); // For Controller
    void deleteBookById(Long id);
    void updateBookById(Long id, BookUpdateDto bookUpdateDto);
}
