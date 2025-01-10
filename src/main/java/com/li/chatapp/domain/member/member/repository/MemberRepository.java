package com.li.chatapp.domain.member.member.repository;

import com.li.chatapp.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    Optional<Member> findByRefreshToken(String refreshToken);
}
