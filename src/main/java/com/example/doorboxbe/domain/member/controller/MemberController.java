package com.example.doorboxbe.domain.member.controller;

import com.example.doorboxbe.domain.auth.annotation.AuthenticatedMember;
import com.example.doorboxbe.domain.member.dto.response.MemberResponseDTO;
import com.example.doorboxbe.domain.member.entity.Member;
import com.example.doorboxbe.domain.member.service.MemberService;
import com.example.doorboxbe.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members/info")
    public ApiResponse<MemberResponseDTO.MemberInfoResponse> getMyInfo(@AuthenticatedMember Member member) {
        MemberResponseDTO.MemberInfoResponse response = memberService.findmyInfo(member);

        return ApiResponse.onSuccess(response);
    }
}
