package com.sisimpur.library.repository;

import com.sisimpur.library.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(" SELECT a From Author a WHERE a.deleteStatus = 'NO' AND a.id = :id ")
    Optional<Author> findAuthorById(@Param("id") Long id);
    @Query(" SELECT a.id AS id, a.biography AS biography, a.name AS name " +
            "   FROM Author a " +
            "   WHERE a.deleteStatus = 'NO' ")
    Page<AuthorRepository.AuthorExt> getAllAuthors(Pageable pageable);
    interface AuthorExt {
        String getName();
        String getBiography();
        Long getId();
    }
}
