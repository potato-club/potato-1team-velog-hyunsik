package com.velog.velogboard.jwt;

import com.velog.velogboard.exception.JwtTokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class TokenProvider {

    private final Environment env;

    public TokenProvider(Environment env) {
        this.env = env;
    }

    //토큰에서 값 추출
    public String validateTokenAndGetUserId(String token) {
        if (!validateToken(token)) {
            throw new JwtTokenException("토큰 오류 입니다.");
        }
        try {
            token = removeBear(token);
            Claims claims = Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            throw new JwtTokenException("토큰 파싱 오류 입니다.");
        }
    }

    public boolean validateToken(String token) {
        try {
            token = removeBear(token);
            Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token);
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

    private String removeBear(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return token;
    }
}
