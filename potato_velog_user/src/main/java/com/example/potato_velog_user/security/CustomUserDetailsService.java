package com.example.potato_velog_user.security;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.repository.AuthRepository;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.utils.error.ErrorCode;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    public CustomUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @SneakyThrows
    @Override
    public PrincipalDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User findUser = authRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return new PrincipalDetails(findUser);
    }


    public String findEmailByUserId(Long id) throws NotFoundException {
        return authRepository.findEmailById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
    }

}
