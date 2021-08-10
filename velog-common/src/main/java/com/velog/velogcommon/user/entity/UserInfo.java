package com.velog.velogcommon.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String velogName;

    private boolean isCommentAlert;

    private boolean isUpdateAlert;

    @OneToOne(mappedBy = "userInfo", fetch = LAZY)
    private User user;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "userSocialInfo_id")
    private UserSocialInfo userSocialInfo;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "userImage_Id")
    private UserImage userImage;


    // 생성자 메소드
    @Builder
    public UserInfo(String velogName, boolean isCommentAlert, boolean isUpdateAlert) {
        this.velogName = velogName;
        this.isCommentAlert = isCommentAlert;
        this.isUpdateAlert = isUpdateAlert;
    }


    //연관 관계 메소드
    public void addUser(User user) {
        this.user = user;
    }

    public void addUserSocialInfo(UserSocialInfo userSocialInfo) {
        this.userSocialInfo = userSocialInfo;
        userSocialInfo.addUserInfo(this);
    }

    public void addUserImage(UserImage userImage) {
        this.userImage = userImage;
        userImage.addUserInfo(this);
    }

    public static UserInfo createUserInfo(String velogName, boolean isCommentAlert, boolean isUpdateAlert) {
        return new UserInfo().builder()
                .velogName(velogName)
                .isCommentAlert(isCommentAlert)
                .isUpdateAlert(isUpdateAlert)
                .build();
    }


    // 스태틱 (생성자) 메소드
    public static UserInfo of(UserInfo userInfo, UserSocialInfo userSocialInfo, UserImage userImage) {
        userInfo.addUserSocialInfo(userSocialInfo);
        userInfo.addUserImage(userImage);
        return userInfo;
    }

}
