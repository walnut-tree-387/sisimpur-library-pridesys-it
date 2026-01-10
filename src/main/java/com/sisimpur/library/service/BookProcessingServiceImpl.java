package com.sisimpur.library.service;

import com.sisimpur.library.dto.BookLentDto;
import com.sisimpur.library.exceptions.types.ResourceNotFoundException;
import com.sisimpur.library.model.AvailableStatus;
import com.sisimpur.library.model.Book;
import com.sisimpur.library.model.BookLent;
import com.sisimpur.library.model.LibraryUser;
import com.sisimpur.library.repository.BookLentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookProcessingServiceImpl implements BookProcessingService{
    private final LibraryUserService libraryUserService;
    private final BookService bookService;
    private final BookLentRepository bookLentRepository;

    public BookProcessingServiceImpl(LibraryUserService libraryUserService, BookService bookService, BookLentRepository bookLentRepository) {
        this.libraryUserService = libraryUserService;
        this.bookService = bookService;
        this.bookLentRepository = bookLentRepository;
    }

    @Override
    public Map<String, List<Long>> lentBooks(BookLentDto bookLentDto) {
        Map<String, List<Long>> response = new HashMap<>();
        response.put("successfully rented", new ArrayList<>());
        response.put("unavailable books", new ArrayList<>());

        LibraryUser user = libraryUserService.getById(bookLentDto.getUserId());
        for(Long bookId: bookLentDto.getBookIdList()){
            Book book = bookService.getById(bookId);
            if(book.getAvailableStatus().equals(AvailableStatus.UNAVAILABLE)){
                response.get("unavailable books").add(bookId);
                continue;
            }
            BookLent bookLent = new BookLent();
            bookLent.setBook(book);
            bookLent.setLibraryUser(user);
            bookLentRepository.save(bookLent);
            bookService.changeAvailabilityStatusById(bookId);
            response.get("successfully rented").add(bookId);
        }
        return response;
    }

    @Override
    public void returnBooks(BookLentDto bookLentDto) {
        LibraryUser user = libraryUserService.getById(bookLentDto.getUserId());
        for(Long bookId: bookLentDto.getBookIdList()){
            BookLent previousEntry = findLentEntryById(user.getId(), bookId);
            bookLentRepository.delete(previousEntry);   // Soft delete could be introduced here, to keep lent history
            bookService.changeAvailabilityStatusById(bookId);
        }
    }
    private BookLent findLentEntryById(Long userId, Long bookId) {
        Optional<BookLent> previousEntry = bookLentRepository.findLentEntry(userId, bookId);
        if(previousEntry.isEmpty()) throw new ResourceNotFoundException(BookProcessingServiceImpl.class,
                "No BookLent entry found with following bookId: " + bookId + ", userId : " + userId);
        return previousEntry.get();
    }
}
