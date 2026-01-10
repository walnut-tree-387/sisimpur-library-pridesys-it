package com.sisimpur.library.dto.books;

import com.sisimpur.library.model.AvailableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookGetDto extends BookBaseDto{
    private String authorName;
    private AvailableStatus availableStatus;
    private Long id;
}
