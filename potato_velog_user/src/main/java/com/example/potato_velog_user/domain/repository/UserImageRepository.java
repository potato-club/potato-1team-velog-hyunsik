package com.example.potato_velog_user.domain.repository;

import com.example.potato_velog_user.domain.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
}
