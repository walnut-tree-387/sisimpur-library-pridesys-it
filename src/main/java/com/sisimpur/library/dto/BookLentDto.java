package com.sisimpur.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookLentDto {
    private Long userId;
    private List<Long> bookIdList = new ArrayList<>();
}
