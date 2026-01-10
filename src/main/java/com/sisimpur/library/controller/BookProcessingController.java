package com.sisimpur.library.controller;

import com.sisimpur.library.dto.BookLentDto;
import com.sisimpur.library.service.BookProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book-processing")
public class BookProcessingController {
    private final BookProcessingService bookProcessingService;

    public BookProcessingController(BookProcessingService bookProcessingService) {
        this.bookProcessingService = bookProcessingService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> lentBooks(@RequestBody BookLentDto bookLentDto) {
        return new ResponseEntity<>(bookProcessingService.lentBooks(bookLentDto), HttpStatus.CREATED);
    }
    @PutMapping("/return")
    public ResponseEntity<?> returnBooks(@RequestBody BookLentDto bookLentDto) {
        bookProcessingService.returnBooks(bookLentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
