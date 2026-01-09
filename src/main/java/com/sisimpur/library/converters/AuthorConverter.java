package com.sisimpur.library.converters;

import com.sisimpur.library.dto.authors.AuthorCreateDto;
import com.sisimpur.library.dto.authors.AuthorGetDto;
import com.sisimpur.library.dto.authors.AuthorUpdateDto;
import com.sisimpur.library.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public Author doCreateMapping(AuthorCreateDto authorCreateDto) {
        Author author = new Author();
        author.setName(authorCreateDto.getName());
        return author;
    }
    public AuthorGetDto doGetMapping(Author author) {
        AuthorGetDto authorGetDto = new AuthorGetDto();
        authorGetDto.setName(author.getName());
        authorGetDto.setId(author.getId());
        return authorGetDto;
    }
    public Author doUpdateMapping(AuthorUpdateDto updateDto, Author previousAuthor) {
        previousAuthor.setName(updateDto.getName());
        return previousAuthor;
    }
}
