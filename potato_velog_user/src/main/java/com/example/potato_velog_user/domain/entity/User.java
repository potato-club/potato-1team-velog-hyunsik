package com.example.potato_velog_user.domain.entity;

import com.example.potato_velog_user.utils.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

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
    private String encodedPassword;
    @Column(nullable = false, unique = true)
    private String nickName;
    @Column(length = 30)
    private String introduce;

    private String userUUId;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;

    //생성자 메소드
    @Builder
    public User(String email, String name, String encodedPassword, String nickName, String introduce) {
        this.email = email;
        this.name = name;
        this.encodedPassword = encodedPassword;
        this.nickName = nickName;
        this.introduce = introduce;
        this.userUUId = UUID.randomUUID().toString();
    }


    // 연관 관계 메소드
    public void addUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        userInfo.addUser(this);
    }


    public static User createUser(String email, String name, String encodedPassword, String nickName, String introduce) {
        return new User().builder()
                .email(email)
                .name(name)
                .encodedPassword(encodedPassword)
                .nickName(nickName)
                .introduce(introduce)
                .build();
    }

    // 스태틱 (생성자) 메소드
    public static User of(User user, UserInfo userInfo) {
        user.addUserInfo(userInfo);
        return user;
    }

    public static User updateNameAndIntroduce(User user, String name, String introduce) {
        user.name = name;
        user.introduce = introduce;
        return user;
    }

}
