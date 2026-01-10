package com.sisimpur.library.repository;

import com.sisimpur.library.model.BookLent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookLentRepository extends JpaRepository<BookLent,Long> {
    @Query("  SELECT bl FROM BookLent bl WHERE bl.book.id = :bookId AND bl.libraryUser.id = :userId ")
    Optional<BookLent> findLentEntry(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
