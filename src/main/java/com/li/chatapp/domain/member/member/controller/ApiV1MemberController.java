package com.li.chatapp.domain.member.member.controller;

import com.li.chatapp.domain.member.member.dto.MemberDto;
import com.li.chatapp.domain.member.member.dto.MemberRequest;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.service.MemberService;
import com.li.chatapp.global.jwt.JwtProvider;
import com.li.chatapp.global.rsData.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public RsData<MemberDto> join(@Valid @RequestBody MemberRequest memberRequest) {
        Member member = memberService.join(memberRequest.getName(), memberRequest.getPassword());

        return new RsData(
                "200",
                "회원가입에 성공하였습니다.",
                new MemberDto(member)
        );
    }
    @PostMapping("/login")
    public RsData<Void> login(@Valid @RequestBody MemberRequest memberRequest, HttpServletResponse response) {

        Member member = memberService.getMemberByName(memberRequest.getName());

        String token = jwtProvider.genAccessToken(member);

        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        return new RsData<>(
                "200",
                "로그인에 성공하였습니다."
        );
    }
    @GetMapping("/logout")
    public void logout() {
        System.out.println("logout");
    }
    @GetMapping("/me")
    public RsData<MemberDto> me(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return new RsData<>(
                    "404",
                    "쿠키가 존재하지 않습니다. 로그인 상태가 아닙니다.",
                    null
            );
        }

        String accessToken = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accessToken")) {
                accessToken = cookie.getValue();
            }
        }

        if (accessToken.isEmpty()) {
            return new RsData<>(
                    "401",
                    "Access Token이 없습니다. 다시 로그인하세요.",
                    null
            );
        }

        Map<String, Object> claims = jwtProvider.getClaims(accessToken);
        String name = (String) claims.get("name");

        Member member = this.memberService.getMemberByName(name);

        return new RsData<>(
                "200",
                "내 정보 조회에 성공하였습니다.",
                new MemberDto(member)
        );
    }
}
