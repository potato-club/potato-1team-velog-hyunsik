package com.velog.veloguser.security.jwt;

import com.velog.veloguser.security.CustomUserDetailsService;
import com.velog.veloguser.security.PrincipalDetails;
import io.jsonwebtoken.*;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.util.Date;



@Slf4j
@Configuration
public class TokenProvider {

    private final CustomUserDetailsService customUserDetailsService;
    private final String secret;
    private final long tokenValidityInMilliseconds;


    public TokenProvider(CustomUserDetailsService customUserDetailsService,
                         @Value("${token.secret}") String secret, @Value("${token.expiration_time}") long tokenValidityInMilliseconds) {
        this.customUserDetailsService = customUserDetailsService;
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }



    public String createToken(String userId) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);


        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Authentication getAuthentication(String token) throws NotFoundException {

        Claims claims = getToken(token);
        String userId = claims.getSubject();

        String email = customUserDetailsService.findEmailByUserId(userId);
        PrincipalDetails principal = customUserDetailsService.loadUserByUsername(email);


        return new UsernamePasswordAuthenticationToken(principal, token, null);
    }

    //토큰에서 값 추출
    public Claims getToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }



}
