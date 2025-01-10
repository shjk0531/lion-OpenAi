package com.li.chatapp.domain.member.member.service;

import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.repository.MemberRepository;
import com.li.chatapp.global.Security.SecurityUser;
import com.li.chatapp.global.jwt.JwtProvider;
import com.li.chatapp.global.rsData.RsData;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService  {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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

    public Member getMemberByName(@NotBlank String name) {
        return memberRepository.findByName(name);
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        return jwtProvider.validate(token);
    }

    // 토큰 갱신
    public RsData<String> refreshAccessToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        String accessToken = jwtProvider.genAccessToken(member);

        return new RsData<>(
                "200",
                "토큰 갱신에 성공하였습니다.",
                accessToken
        );
    }

    // 토큰으로 유저 정보 가져오기
    public SecurityUser getUserFromAccessToken(String accessToken) {
        Map<String, Object> payloadBody = jwtProvider.getClaims(accessToken);
        long id = (int) payloadBody.get("id");
        String name = (String) payloadBody.get("name");
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new SecurityUser(id, name, "", authorities);
    }
}
