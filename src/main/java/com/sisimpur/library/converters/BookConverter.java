package com.sisimpur.library.converters;

import com.sisimpur.library.dto.books.BookBaseDto;
import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.dto.books.BookGetDto;
import com.sisimpur.library.dto.books.BookUpdateDto;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    public Book doCreateMapping(BookCreateDto dto, Author author) {
        Book book = new Book();
        mapBaseDtoToEntity(dto, book);
        book.setAuthor(author);
        return book;
    }
    public BookGetDto doGetMapping(Book book) {
        BookGetDto bookGetDto = new BookGetDto();
        mapEntityToBaseDto(book, bookGetDto);
        bookGetDto.setAuthorName(book.getAuthor().getName());
        return bookGetDto;
    }
    public Book doUpdateMapping(BookUpdateDto bookUpdateDto, Book previousBook, Author author) {
        mapBaseDtoToEntity(bookUpdateDto, previousBook);
        previousBook.setAuthor(author);
        return previousBook;
    }
    public void mapBaseDtoToEntity(BookBaseDto dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setTitle(dto.getTitle());
        book.setGenre(dto.getGenre());
    }
    public void mapEntityToBaseDto(Book book, BookGetDto dto) {
        dto.setTitle(book.getTitle());
        dto.setTitle(book.getTitle());
        dto.setGenre(book.getGenre());
        dto.setStatus(book.getStatus());
    }
}
