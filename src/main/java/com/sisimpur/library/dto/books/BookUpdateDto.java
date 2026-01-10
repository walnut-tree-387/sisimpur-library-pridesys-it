package com.sisimpur.library.dto.books;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateDto extends BookBaseDto{
    private Long authorId;
}
