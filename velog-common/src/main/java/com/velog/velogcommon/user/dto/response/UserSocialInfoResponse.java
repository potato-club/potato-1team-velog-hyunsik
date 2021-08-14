package com.velog.velogcommon.user.dto.response;

import com.velog.velogcommon.user.entity.UserSocialInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSocialInfoResponse {

        private String email;

        private String github;

        private String twitter;

        private String facebook;

        private String homePage;

        @Builder
        public UserSocialInfoResponse(String email, String github, String twitter, String facebook, String homePage) {
            this.email = email;
            this.github = github;
            this.twitter = twitter;
            this.facebook = facebook;
            this.homePage = homePage;
        }

        public static UserSocialInfoResponse of(UserSocialInfo socialInfo) {
            return new UserSocialInfoResponse().builder().email(socialInfo.getEmail()).github(socialInfo.getGithub())
                    .twitter(socialInfo.getTwitter()).facebook(socialInfo.getFacebook()).homePage(socialInfo.getHomePage())
                    .build();
        }

}
