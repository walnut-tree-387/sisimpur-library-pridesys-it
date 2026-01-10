package com.sisimpur.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "library_users")
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(unique = true, length = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserStatus status =  UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus = DeleteStatus.NO;
}
