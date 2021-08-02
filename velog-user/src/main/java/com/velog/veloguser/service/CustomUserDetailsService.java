package com.velog.veloguser.service;

import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.domain.entity.User;
import com.velog.veloguser.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User findUser = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("해당 하는 계정 찾을 수 업써요"));

        return new org.springframework.security.core.userdetails.User(findUser.getEmail(), findUser.getEncodedPassword(), true, true, true, true, new ArrayList<>());
    }

    public UserResponse findUserByEmail(String email) throws NotFoundException {
        User findUser = userRepository.findUserByEmail(email).orElseThrow(
                () -> new NotFoundException("해당하는 유저를 찾을 수 없습니다.")
        );
        return UserResponse.of(findUser.getEmail(), findUser.getName(), findUser.getUserId());
    }
}
