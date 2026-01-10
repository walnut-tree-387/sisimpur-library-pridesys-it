package com.sisimpur.library.service;

import com.sisimpur.library.dto.BookLentDto;

import java.util.List;
import java.util.Map;

public interface BookProcessingService {
    Map<String, List<Long>> lentBooks(BookLentDto bookLentDto);
    void returnBooks(BookLentDto bookLentDto);
}
