package com.velog.velogcommon.user.entity;

import com.velog.velogcommon.user.dto.request.SocialInfoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSocialInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String github;

    private String twitter;

    private String facebook;

    private String homePage;

    @OneToOne(mappedBy = "userSocialInfo", fetch = LAZY)
    private UserInfo userInfo;


    @Builder
    public UserSocialInfo(String email, String github, String twitter, String facebook, String homePage) {
        this.email = email;
        this.github = github;
        this.twitter = twitter;
        this.facebook = facebook;
        this.homePage = homePage;
    }


    // 연관 관계 메소드
    public void addUserInfo(UserInfo userInfo) {
        this.userInfo= userInfo;
    }

    public void update(SocialInfoRequest request) {
        this.email = request.getEmail();
        this.github = request.getGithub();
        this.twitter = request.getTwitter();
        this.facebook = request.getFacebook();
        this.homePage = request.getHomePage();
    }
    //생성자 메소드
    public static UserSocialInfo of(String email, String github, String twitter, String facebook, String homePage) {
       return new UserSocialInfo().builder()
               .email(email)
               .github(github)
               .twitter(twitter)
               .facebook(facebook)
               .homePage(homePage)
               .build();
    }


}
