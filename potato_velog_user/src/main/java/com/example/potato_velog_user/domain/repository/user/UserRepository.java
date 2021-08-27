package com.example.potato_velog_user.domain.repository.user;

import com.example.potato_velog_user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);

    Optional<User> findByNickName(String nickName);

    void deleteUserImageById(Long userId);

}
