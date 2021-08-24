package com.example.potato_velog_user.domain.repository;

import com.example.potato_velog_user.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
