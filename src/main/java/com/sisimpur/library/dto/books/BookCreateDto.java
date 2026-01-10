package com.sisimpur.library.dto.books;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateDto extends BookBaseDto{
    @NotNull(message = "Author id is mandatory to create book")
    private Long authorId;
}
