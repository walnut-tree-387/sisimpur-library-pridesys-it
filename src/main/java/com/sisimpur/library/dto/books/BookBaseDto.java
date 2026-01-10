package com.sisimpur.library.dto.books;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBaseDto {
    @NotBlank(message = "Book title is mandatory")
    private String title;
    @NotBlank(message = "Book genre is mandatory")
    private String genre;
    @NotNull(message = "Published Year is mandatory")
    private Long publishedYear;
}
