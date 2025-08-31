package com.example.doorboxbe.domain.member.dto.response;

import com.example.doorboxbe.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class MemberInfoResponse {
        private String email;
        private String username;
        private String image;

        public static MemberInfoResponse toMemberInfoResponse(Member member) {
            return MemberInfoResponse.builder()
                    .username(member.getUsername())
                    .image(member.getProfileImage())
                    .build();
        }
    }
}
