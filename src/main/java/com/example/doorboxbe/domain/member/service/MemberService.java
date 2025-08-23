package com.example.doorboxbe.domain.member.service;

import com.example.doorboxbe.domain.member.dto.response.MemberResponseDTO;
import com.example.doorboxbe.domain.member.entity.Member;

public interface MemberService {

    Member findOrCreateKakaoMember(String providerId, String username, String image);

    Member findById(Long id);

    MemberResponseDTO.MemberInfoResponse findmyInfo(Member member);
}
