package com.sisimpur.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sisimpur.library.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query(" SELECT b From Book b WHERE b.deleteStatus = 'NO' AND b.id = :id ")
    Optional<Book> findBookById(@Param("id") Long id);
    List<Book> findBooksByAuthorId(Long authorId);
}