package com.sisimpur.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "published_year")
    private Long publishedYear;

    @Enumerated(EnumType.STRING)
    private AvailableStatus availableStatus = AvailableStatus.AVAILABLE;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus = DeleteStatus.NO;
}
