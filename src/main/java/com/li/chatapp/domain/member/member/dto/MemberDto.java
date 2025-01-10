package com.li.chatapp.domain.member.member.dto;

import com.li.chatapp.domain.member.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private String name;

    public MemberDto(Member member) {
        this.name = member.getName();
    }
}
