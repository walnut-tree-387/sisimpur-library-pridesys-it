package com.sisimpur.library.service;

import com.sisimpur.library.dto.users.LibraryUserCreateDto;
import com.sisimpur.library.dto.users.LibraryUserGetDto;
import com.sisimpur.library.dto.users.LibraryUserUpdateDto;
import com.sisimpur.library.model.LibraryUser;
import com.sisimpur.library.repository.LibraryUserRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface LibraryUserService {
    LibraryUser getById(Long id);
    LibraryUserGetDto create(LibraryUserCreateDto createDto);
    void delete(Long id);
    void update(Long id, LibraryUserUpdateDto updateDto);
    LibraryUserGetDto getUserById(Long id);
    void changeUserStatus(Long id);
    Page<LibraryUserRepository.LibraryUserExt> getAllUsers(Optional<Integer> page, Optional<Integer> size);
}
