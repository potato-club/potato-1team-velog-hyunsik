package com.velog.velogcommon.user.entity;

import com.velog.velogcommon.board.entity.Board;
import com.velog.velogcommon.utils.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

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

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private final List<Board> boardList = new ArrayList<>();

    @Builder
    public User(String email, String name, String encodedPassword, String nickName, String introduce) {
        this.email = email;
        this.name = name;
        this.encodedPassword = encodedPassword;
        this.nickName = nickName;
        this.introduce = introduce;
    }

    // 연관 관계 메소드
    public void addUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        userInfo.addUser(this);
    }

    public void addBoard(Board board) {
        boardList.add(board);
        board.addUser(this);
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

    public static User of(User user, UserInfo userInfo) {
        user.addUserInfo(userInfo);
        return user;
    }

}
