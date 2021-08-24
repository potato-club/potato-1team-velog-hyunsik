package com.example.potato_velog_user.web.dto.user.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialInfoRequest {


        private String email;

        private String github;

        private String twitter;

        private String facebook;

        private String homePage;

        public SocialInfoRequest(String email, String github, String twitter, String facebook, String homePage) {
            this.email = email;
            this.github = github;
            this.twitter = twitter;
            this.facebook = facebook;
            this.homePage = homePage;
        }

}
