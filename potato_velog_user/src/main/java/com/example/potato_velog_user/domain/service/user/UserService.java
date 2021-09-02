package com.example.potato_velog_user.domain.service.user;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserInfo;
import com.example.potato_velog_user.domain.entity.UserSocialInfo;
import com.example.potato_velog_user.exception.AlreadyExistException;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.web.dto.user.request.SocialInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import com.example.potato_velog_user.web.dto.user.response.UserFeignResponse;
import org.springframework.validation.BindException;


public interface UserService {


    User createUser(UserRequest.Create request) throws NotFoundException, AlreadyExistException, BindException;

    User updateNameAndIntroduce(UserRequest.UpdateNameAndIntroduce request, String uuId) throws NotFoundException;

    UserSocialInfo updateSocialInfo(SocialInfoRequest request, String uuId);

    UserInfo updateUserInfo(UserInfoRequest request, String uuId);

    UserFeignResponse getUser(String token);
}
