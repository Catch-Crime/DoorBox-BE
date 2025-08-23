package com.example.doorboxbe.domain.auth.dto.request;

import com.example.doorboxbe.domain.member.entity.enums.ProviderType;
import lombok.Builder;
import lombok.Getter;

public class OAuth2RequestDTO {

    @Getter
    @Builder
    public static class OAuth2LoginRequest {
        private String email;
        private String nickname;
        private ProviderType providerType;
        private String image;
        private String providerId;
    }
}
