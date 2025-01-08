package com.li.chatapp.domain.member.member.service;

import com.li.chatapp.domain.global.rsData.RsData;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService  {
    private final MemberRepository memberRepository;

    public RsData<Member> join(String name, String password) {
        Member member = Member.builder()
                .name(name)
                .password(password)
                .build();

        memberRepository.save(member);

        return RsData.of("200", "%s 님 가입을 환영합니다.".formatted(name), member);
    }
}
