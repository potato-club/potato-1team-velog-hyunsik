package com.example.potato_velog_user.domain.repository;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserUUId(String uuId);

    Optional<User> findByNickName(String nickName);

}
