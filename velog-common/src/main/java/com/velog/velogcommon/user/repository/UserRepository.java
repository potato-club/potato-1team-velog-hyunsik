package com.velog.velogcommon.user.repository;

import com.velog.velogcommon.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Query("select u.encodedPassword from User u where u.email = :email")
    Optional<String> findEncodedPasswordByEmail(@Param("email") String email);

    @Query("select u.email from User u where u.userId = :userId")
    Optional<String> findEmailByUserId(@Param("userId") String userId);

    Optional<User> findUserByUserId(String userId);
}
