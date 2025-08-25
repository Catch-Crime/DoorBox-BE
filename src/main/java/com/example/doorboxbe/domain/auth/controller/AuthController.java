package com.example.doorboxbe.domain.auth.controller;

import com.example.doorboxbe.domain.auth.dto.request.AuthRequestDTO;
import com.example.doorboxbe.domain.auth.dto.response.AuthResponseDTO;
import com.example.doorboxbe.domain.auth.service.AuthService;
import com.example.doorboxbe.domain.auth.service.KakaoVerifier;
import com.example.doorboxbe.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final KakaoVerifier kakaoVerifier;


    @PostMapping("/api/oauth2/login/kakao")
    public ApiResponse<AuthResponseDTO.LoginResponse> kakaoLogin(@RequestBody AuthRequestDTO.LoginRequest request) {

        System.out.println("[AuthController] 요청 들어옴, idToken = " + request.getIdToken());

        KakaoVerifier.KakaoOidcUser kakaoUser = kakaoVerifier.verify(request.getIdToken());
        System.out.println("[AuthController] 검증 완료: " + kakaoUser);

        AuthResponseDTO.LoginResponse response = authService.login(request);
        System.out.println("[AuthController] login 처리 완료");

        return ApiResponse.onSuccess(response);

    }
}
