package com.sisimpur.library.repository;

import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser,Long> {
    @Query(" SELECT lu From LibraryUser lu WHERE lu.deleteStatus = 'NO' AND lu.id = :id ")
    Optional<LibraryUser> findLibraryUserById(@Param("id") Long id);
}
