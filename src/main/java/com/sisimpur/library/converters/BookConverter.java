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
        bookGetDto.setId(book.getId());
        bookGetDto.setAvailableStatus(book.getAvailableStatus());
        return bookGetDto;
    }
    public Book doUpdateMapping(BookUpdateDto bookUpdateDto, Book previousBook, Author author) {
        mapBaseDtoToEntity(bookUpdateDto, previousBook);
        if(author != null)previousBook.setAuthor(author);
        return previousBook;
    }
    public void mapBaseDtoToEntity(BookBaseDto dto, Book book) {
        if(dto.getTitle() != null)book.setTitle(dto.getTitle());
        if(dto.getPublishedYear() != null)book.setPublishedYear(dto.getPublishedYear());
        if(dto.getGenre() != null)book.setGenre(dto.getGenre());
    }
    public void mapEntityToBaseDto(Book book, BookGetDto dto) {
        dto.setTitle(book.getTitle());
        dto.setPublishedYear(book.getPublishedYear());
        dto.setGenre(book.getGenre());
    }
}
