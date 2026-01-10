package com.sisimpur.library.service;

import com.sisimpur.library.converters.AuthorConverter;
import com.sisimpur.library.dto.authors.AuthorCreateDto;
import com.sisimpur.library.dto.authors.AuthorGetDto;
import com.sisimpur.library.dto.authors.AuthorUpdateDto;
import com.sisimpur.library.exceptions.types.ResourceNotFoundException;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.DeleteStatus;
import com.sisimpur.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    @Override
    public Author getById(Long id) {
        Optional<Author> authorOp = authorRepository.findAuthorById(id);
        if(authorOp.isEmpty()){
            throw new ResourceNotFoundException(AuthorServiceImpl.class, "No Author found with id : " + id);
        }
        return authorOp.get();
    }

    @Override
    public AuthorGetDto create(AuthorCreateDto createDto) {
        Author newAuthor = authorConverter.doCreateMapping(createDto);
        newAuthor = authorRepository.save(newAuthor);
        return authorConverter.doGetMapping(newAuthor);
    }

    @Override
    public void delete(Long id) {
        Author author = getById(id);
        author.setDeleteStatus(DeleteStatus.YES);
        authorRepository.save(author);
    }

    @Override
    public void update(Long id, AuthorUpdateDto updateDto) {
        Author author = getById(id);
        author = authorConverter.doUpdateMapping(updateDto, author);
        authorRepository.save(author);
    }

    @Override
    public AuthorGetDto getAuthorById(Long id) {
        return authorConverter.doGetMapping(getById(id));
    }

    @Override
    public Page<AuthorRepository.AuthorExt> getAllAuthors(Optional<Integer> page, Optional<Integer> size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), sort);
        return authorRepository.getAllAuthors(pageable);
    }
}
