package com.velog.velogcommon.user.repository;

import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select u.encodedPassword from User u where u.email = :email")
    Optional<String> findEncodedPasswordByEmail(@Param("email") String email);

    @Query("select u.email from User u where u.id = :id")
    Optional<String> findEmailById(@Param("id") Long id);

    Optional<User> findById(Long id);

    Optional<User> findByNickName(String nickName);

    Optional<UserInfo> findUserInfoById(Long userId);
}
