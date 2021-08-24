package com.example.potato_velog_user.domain.repository;

import com.example.potato_velog_user.domain.entity.UserSocialInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSocialInfoRepository extends JpaRepository<UserSocialInfo, Long> {
}
