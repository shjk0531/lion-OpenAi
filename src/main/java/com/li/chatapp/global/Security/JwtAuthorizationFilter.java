package com.li.chatapp.global.Security;

import com.li.chatapp.domain.member.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        if (request.getRequestURI().equals("/api/v1/members/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = _getCookie("accessToken");
    }

    private String _getCookie(String name) {
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst()
                .map(Cookie::getValue)
                .orElse("");
    }

    private void _addHeaderCookie(String tokenName, String token) {
        ResponseCookie cookie = ResponseCookie.from(tokenName, token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
