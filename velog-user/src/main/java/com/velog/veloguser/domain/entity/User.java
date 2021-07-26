package com.velog.veloguser.domain.entity;

import com.velog.veloguser.domain.utils.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encodedPassword;

    @Builder
    public User(String email, String name, String userId, String encodedPassword) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encodedPassword = encodedPassword;
    }

    public static User of(String email, String name, String userId, String encodedPassword) {
        return new User().builder()
                .email(email)
                .name(name)
                .userId(userId)
                .encodedPassword(encodedPassword)
                .build();
    }
}
