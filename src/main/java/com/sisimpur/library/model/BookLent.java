package com.sisimpur.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "book_lent")
@NoArgsConstructor
@AllArgsConstructor
public class BookLent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    private LibraryUser libraryUser;
}
