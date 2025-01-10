package com.li.chatapp.domain.member.member.controller;

import com.li.chatapp.domain.member.member.dto.MemberRequest;
import com.li.chatapp.domain.member.member.service.MemberService;
import com.li.chatapp.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public RsData<String> join(@Valid @RequestBody MemberRequest memberRequest) {
        memberService.join(memberRequest.getName(), memberRequest.getPassword());

        return RsData.of(
                "200",
                "회원가입에 성공하였습니다.",
                memberRequest.getName()
        );
    }
    @PostMapping("/login")
    public void login() {
        System.out.println("login");
    }
    @GetMapping("/logout")
    public void logout() {
        System.out.println("logout");
    }
    @GetMapping("/me")
    public void me() {
        System.out.println("me");
    }
}
