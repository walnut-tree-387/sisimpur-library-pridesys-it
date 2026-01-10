package com.sisimpur.library.dto.authors;

import com.sisimpur.library.dto.books.BookCreateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class AuthorCreateDto extends AuthorBaseDto{
    private List<BookCreateDto> authorBooks = new ArrayList<>();
}
