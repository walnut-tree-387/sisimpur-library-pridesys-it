package com.sisimpur.library.dto.users;

import com.sisimpur.library.model.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryUserGetDto extends LibraryUserBaseDto{
    private Long id;
    private UserStatus status;
}
