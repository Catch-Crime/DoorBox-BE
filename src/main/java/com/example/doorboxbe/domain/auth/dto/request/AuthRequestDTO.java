package com.example.doorboxbe.domain.auth.dto.request;

import lombok.Builder;
import lombok.Getter;

public class AuthRequestDTO {

    @Getter
    @Builder
    public static class LoginRequest {
        private final String idToken;
    }

    @Getter
    public static class RefreshTokenRequest {
        private String refreshToken;
    }
}
