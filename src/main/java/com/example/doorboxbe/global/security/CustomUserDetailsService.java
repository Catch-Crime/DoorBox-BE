package com.example.doorboxbe.global.security;


import com.example.doorboxbe.domain.member.entity.Member;
import com.example.doorboxbe.domain.member.repository.MemberRepository;
import com.example.doorboxbe.global.apiPayload.code.status.error.MemberErrorStatus;
import com.example.doorboxbe.global.apiPayload.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetails loadUserById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorStatus.NOT_FOUND));

        return new CustomUserDetails(member);
    }
}
