package com.example.doorboxbe.domain.member.service;

import com.example.doorboxbe.domain.member.dto.response.MemberResponseDTO;
import com.example.doorboxbe.domain.member.entity.Member;
import com.example.doorboxbe.domain.member.entity.enums.ProviderType;
import com.example.doorboxbe.domain.member.repository.MemberRepository;
import com.example.doorboxbe.global.apiPayload.code.status.error.MemberErrorStatus;
import com.example.doorboxbe.global.apiPayload.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findOrCreateKakaoMember(String providerId, String username, String image) {
        System.out.println("memberService에서 데이터 저장값:" + providerId + username + image);
        return memberRepository.findByProviderId(providerId)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .providerId(providerId)
                                .username(username)
                                .profileImage(image)
                                .providerType(ProviderType.KAKAO)
                                .build()
                ));
    }


    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new MemberException(MemberErrorStatus.NOT_FOUND));
    }

    @Override
    public MemberResponseDTO.MemberInfoResponse findmyInfo(Member member) {

        Member memberInfo = memberRepository.findById(member.getId()).orElseThrow(()
                -> new MemberException(MemberErrorStatus.NOT_FOUND));

        return MemberResponseDTO.MemberInfoResponse.toMemberInfoResponse(memberInfo);
    }

}
