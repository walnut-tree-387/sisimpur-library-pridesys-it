package com.sisimpur.library.service;

import com.sisimpur.library.converters.BookConverter;
import com.sisimpur.library.dto.books.BookCreateDto;
import com.sisimpur.library.dto.books.BookGetDto;
import com.sisimpur.library.dto.books.BookUpdateDto;
import com.sisimpur.library.exceptions.types.ResourceNotFoundException;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.DeleteStatus;
import com.sisimpur.library.repository.predicates.BookPredicateFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sisimpur.library.model.Book;
import com.sisimpur.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, BookConverter bookConverter, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.authorService = authorService;
    }
    @Override
    public Book getById(Long id) {
        Optional<Book> bookOp = bookRepository.findBookById(id);
        if(bookOp.isEmpty()) {
            throw new ResourceNotFoundException(BookServiceImpl.class, "No book found with id " + id);
        }
        return bookOp.get();
    }

    @Override
    public BookGetDto getBookById(Long id) {
        Book book = getById(id);
        return bookConverter.doGetMapping(book);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = getById(id);
        book.setDeleteStatus(DeleteStatus.YES);
        bookRepository.save(book);
    }

    @Override
    public void update(Long id, BookUpdateDto bookUpdateDto) {
        Book book = getById(id);
        Author newAuthor = (bookUpdateDto.getAuthorId() != null) ? authorService.getById(bookUpdateDto.getAuthorId()) : null;
        book = bookConverter.doUpdateMapping(bookUpdateDto, book, newAuthor);
        bookRepository.save(book);
    }

    @Override
    public Page<BookGetDto> searchBooks(Map<String, Object> searchParams, Optional<Integer> page, Optional<Integer> size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), sort);
        return bookRepository.findAll(BookPredicateFactory.searchBook(searchParams), pageable)
                .map(bookConverter::doGetMapping);
    }

    @Override
    public BookGetDto create(BookCreateDto createDto) {
        Author bookAuthor = authorService.getById(createDto.getAuthorId());
        Book newBook = bookConverter.doCreateMapping(createDto, bookAuthor);
        newBook = bookRepository.save(newBook);
        return bookConverter.doGetMapping(newBook);
    }
}
