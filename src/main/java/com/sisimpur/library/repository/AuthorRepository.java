package com.sisimpur.library.repository;

import com.sisimpur.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(" SELECT a From Author a WHERE a.deleteStatus = 'NO' AND a.id = :id ")
    Optional<Author> findAuthorById(@Param("id") Long id);

}
