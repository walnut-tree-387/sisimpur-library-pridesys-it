package com.sisimpur.library.dto.books;

import com.sisimpur.library.model.AvailableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBaseDto {
    private String title;
    private String genre;
    private int publishedYear;
    private AvailableStatus status;
}
