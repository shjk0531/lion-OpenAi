package com.li.chatapp.domain.member.member.service;

import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService  {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(String name, String password) {
        Member CheckedSignUpMember = memberRepository.findByName(name);

        if (CheckedSignUpMember != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .build();

       return memberRepository.save(member);
    }

    public Optional<Member> findById(long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member findByName(String userName) {
        return memberRepository.findByName(userName);
    }
}
