package com.velog.veloguser.service;

import com.velog.veloguser.domain.entity.User;
import com.velog.veloguser.repository.UserRepository;
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
        User findUser = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("{user.notFound}"));

        return new org.springframework.security.core.userdetails.User(findUser.getEmail(), findUser.getEncodedPassword(), true, true, true, true, new ArrayList<>());
    }
}
