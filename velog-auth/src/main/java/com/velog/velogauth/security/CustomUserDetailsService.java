package com.velog.velogauth.security;

import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.error.ErrorCode;
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
        User findUser = userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return new PrincipalDetails(findUser);
    }


    public String findEmailByUserId(Long id) throws NotFoundException {
        return userRepository.findEmailById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
    }

}
