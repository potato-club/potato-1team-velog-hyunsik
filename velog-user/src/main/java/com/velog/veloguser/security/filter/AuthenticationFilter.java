package com.velog.veloguser.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.velog.veloguser.domain.dto.request.LoginRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.service.CustomUserDetailsService;
import com.velog.veloguser.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private CustomUserDetailsService customUserDetailsService;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
        this.env = env;
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {

            // Post날라오기 때문에 InputStream으로 받아서 인증 객체 생성
            LoginRequest credentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            // AuthenticationManager에게 인증 토큰 전달
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String email = ((User) authResult.getPrincipal()).getUsername();
        UserResponse userDetails = customUserDetailsService.findUserByEmail(email);



        String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(Objects.requireNonNull(env.getProperty("token.expiration_time")))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();



        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());
    }
}
