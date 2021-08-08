package com.velog.velogauth.security;

import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.repository.UserRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public PrincipalDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User findUser = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("해당 하는 계정 찾을 수 업써요"));
        return new PrincipalDetails(findUser);
    }


    public String findEmailByUserId(String userId) throws NotFoundException {
        return userRepository.findEmailByUserId(userId).orElseThrow(() -> new NotFoundException("해당 계정을 찾을 수 없습니다."));
    }

}