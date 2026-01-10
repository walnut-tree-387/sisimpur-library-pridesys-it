package com.sisimpur.library.repository;

import com.sisimpur.library.model.Author;
import com.sisimpur.library.model.LibraryUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser,Long> {
    @Query(" SELECT lu From LibraryUser lu WHERE lu.deleteStatus = 'NO' AND lu.id = :id ")
    Optional<LibraryUser> findLibraryUserById(@Param("id") Long id);

    @Query(" SELECT lu.id AS id, lu.email AS email, lu.name AS name, lu.status AS status " +
            "   FROM LibraryUser lu " +
            "   WHERE lu.deleteStatus = 'NO' ")
    Page<LibraryUserExt> getAllUsers(Pageable pageable);
    interface LibraryUserExt{
        Long getId();
        String getEmail();
        String getName();
        String getStatus();
    }
}
