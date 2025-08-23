package com.example.doorboxbe.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

public class AuthResponseDTO {

    @Getter
    @Builder
    public static class LoginResponse {
        private String accessToken;
        private String refreshToken;

        public static LoginResponse toLoginResponse(String accessToken, String refreshToken) {
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }



    @Getter
    @Builder
    public static class RefreshTokenResponse {
        private String accessToken;

        public static RefreshTokenResponse torefreshTokenResponse(String accessToken) {
            return RefreshTokenResponse.builder()
                    .accessToken(accessToken)
                    .build();
        }
    }
}
