package com.example.potato_velog_user.domain.repository.auth;

import com.example.potato_velog_user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select u.email from User u where u.id = :id")
    Optional<String> findEmailById(@Param("id") Long id);

    Optional<User> findById(Long id);


}
