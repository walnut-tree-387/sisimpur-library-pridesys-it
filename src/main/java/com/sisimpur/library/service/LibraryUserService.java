package com.sisimpur.library.service;

import com.sisimpur.library.dto.users.LibraryUserCreateDto;
import com.sisimpur.library.dto.users.LibraryUserGetDto;
import com.sisimpur.library.dto.users.LibraryUserUpdateDto;
import com.sisimpur.library.model.LibraryUser;

public interface LibraryUserService {
    LibraryUser getById(Long id);
    LibraryUserGetDto create(LibraryUserCreateDto createDto);
    void delete(Long id);
    void update(Long id, LibraryUserUpdateDto updateDto);
    LibraryUserGetDto getUserById(Long id);
}
