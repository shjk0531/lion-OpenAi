package com.li.chatapp.domain.member.member.dto;

import com.li.chatapp.domain.member.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String password;

    public MemberRequest(Member member) {
        this.name = member.getName();
        this.password = member.getPassword();
    }
}
