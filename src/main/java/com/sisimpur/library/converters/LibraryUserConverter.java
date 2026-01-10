package com.sisimpur.library.converters;

import com.sisimpur.library.dto.users.LibraryUserCreateDto;
import com.sisimpur.library.dto.users.LibraryUserGetDto;
import com.sisimpur.library.dto.users.LibraryUserUpdateDto;
import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.LibraryUser;
import org.springframework.stereotype.Component;

@Component
public class LibraryUserConverter {
    public LibraryUser doCreateMapping(LibraryUserCreateDto dto) {
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setName(dto.getName());
        libraryUser.setEmail(dto.getEmail());
        return libraryUser;
    }
    public LibraryUserGetDto doGetMapping(LibraryUser libraryUser) {
        LibraryUserGetDto libraryUserGetDto = new LibraryUserGetDto();
        libraryUserGetDto.setName(libraryUser.getName());
        libraryUserGetDto.setEmail(libraryUser.getEmail());
        libraryUserGetDto.setId(libraryUser.getId());
        libraryUserGetDto.setStatus(libraryUser.getStatus());
        return libraryUserGetDto;
    }
    public LibraryUser doUpdateMapping(LibraryUserUpdateDto dto, LibraryUser libraryUser) {
        if(dto.getName() != null)libraryUser.setName(dto.getName());
        if(dto.getEmail() != null)libraryUser.setEmail(dto.getEmail());
        return libraryUser;
    }
}
