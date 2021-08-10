package com.velog.velogauth.security.jwt;

import com.velog.velogcommon.exception.JwtTokenException;
import com.velog.velogcommon.utils.error.ErrorCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class JwtFilter extends GenericFilterBean {

    private static final String[] whiteList = {"/", "/createUser", "/authenticate", "/validateToken", "/css/*"};


    private final TokenProvider tokenProvider;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        log.info("requestURI = {}", requestURI);

        if (isWhileListPath(requestURI)) {
            log.info("이놈들은 token 유효성 검사가 필요없는 url들 입니다., uri: {}", requestURI);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        if (!(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt))) {
            log.info("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
            throw new JwtTokenException(ErrorCode.JWT_TOKEN_EXCEPTION_INVALID);
        }


        Authentication authentication = tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Security Context 에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);


        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * 화이트 리스트의 경우 인증 체크 X
     */
    private boolean isWhileListPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
