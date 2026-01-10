package com.sisimpur.library.dto.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryUserBaseDto {
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Name is mandatory")
    private String name;
}
