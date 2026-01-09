package com.sisimpur.library.repository;

import com.sisimpur.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sisimpur.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(" SELECT b From Book b WHERE b.deleteStatus = 'NO' AND b.id = :id ")
    Optional<Book> findBookById(@Param("id") Long id);
}