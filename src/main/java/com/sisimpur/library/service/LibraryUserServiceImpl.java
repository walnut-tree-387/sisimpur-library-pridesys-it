package com.sisimpur.library.service;

import com.sisimpur.library.converters.LibraryUserConverter;
import com.sisimpur.library.dto.users.LibraryUserCreateDto;
import com.sisimpur.library.dto.users.LibraryUserGetDto;
import com.sisimpur.library.dto.users.LibraryUserUpdateDto;
import com.sisimpur.library.exceptions.types.ResourceNotFoundException;
import com.sisimpur.library.model.DeleteStatus;
import com.sisimpur.library.model.LibraryUser;
import com.sisimpur.library.repository.LibraryUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{
    private final LibraryUserRepository libraryUserRepository;
    private final LibraryUserConverter  libraryUserConverter;

    public LibraryUserServiceImpl(LibraryUserRepository libraryUserRepository, LibraryUserConverter libraryUserConverter) {
        this.libraryUserRepository = libraryUserRepository;
        this.libraryUserConverter = libraryUserConverter;
    }

    @Override
    public LibraryUser getById(Long id) {
        Optional<LibraryUser> userOp = libraryUserRepository.findLibraryUserById(id);
        if(userOp.isEmpty()){
            throw new ResourceNotFoundException(AuthorServiceImpl.class, "No Library user found with id : " + id);
        }
        return userOp.get();
    }

    @Override
    public LibraryUserGetDto create(LibraryUserCreateDto createDto) {
        LibraryUser libraryUser = libraryUserConverter.doCreateMapping(createDto);
        libraryUser = libraryUserRepository.save(libraryUser);
        return libraryUserConverter.doGetMapping(libraryUser);
    }

    @Override
    public void delete(Long id) {
        LibraryUser libraryUser = getById(id);
        libraryUser.setDeleteStatus(DeleteStatus.YES);
        libraryUserRepository.save(libraryUser);
    }

    @Override
    public void update(Long id, LibraryUserUpdateDto updateDto) {
        LibraryUser libraryUser = getById(id);
        libraryUser = libraryUserConverter.doUpdateMapping(updateDto, libraryUser);
        libraryUserRepository.save(libraryUser);
    }

    @Override
    public LibraryUserGetDto getUserById(Long id) {
        return libraryUserConverter.doGetMapping(getById(id));
    }

    @Override
    public Page<LibraryUserRepository.LibraryUserExt> getAllUsers(Optional<Integer> page, Optional<Integer> size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), sort);
        return libraryUserRepository.getAllUsers(pageable);
    }
}
