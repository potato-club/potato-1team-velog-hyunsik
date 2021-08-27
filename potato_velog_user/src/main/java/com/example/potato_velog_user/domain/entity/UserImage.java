package com.example.potato_velog_user.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @OneToOne(mappedBy = "userImage", fetch = LAZY)
    private User user;

    @Builder
    public UserImage(String originalImageName, String uploadImageUrl, String uploadImageName) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
    }

    //연관 관계 메소드
    public void addUser(User user) {
        this.user = user;
    }

    public static UserImage of(String originalImageName, String uploadImageUrl, String uploadImageName) {
        return new UserImage().builder()
                .originalImageName(originalImageName)
                .uploadImageUrl(uploadImageUrl)
                .uploadImageName(uploadImageName)
                .build();
    }


}
