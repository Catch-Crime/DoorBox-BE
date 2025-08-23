package com.example.doorboxbe.domain.auth.service;

import com.example.doorboxbe.domain.auth.dto.request.AuthRequestDTO;
import com.example.doorboxbe.domain.auth.dto.response.AuthResponseDTO;
import com.example.doorboxbe.domain.member.entity.Member;
import com.example.doorboxbe.domain.member.service.MemberService;
import com.example.doorboxbe.global.apiPayload.code.status.error.TokenErrorStatus;
import com.example.doorboxbe.global.apiPayload.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KakaoVerifier kakaoVerifier;
    private final MemberService memberService;
    private final TokenService tokenService;


    public AuthResponseDTO.LoginResponse login(AuthRequestDTO.LoginRequest request) {
        String idToken = request.getIdToken();
        if (idToken == null || idToken.isBlank()) {
            throw new TokenException(TokenErrorStatus.INVALID_ID_TOKEN);
        }

        // 1) 카카오가 서명한 id_token 검증 + 클레임 파싱
        var kakaoUser = kakaoVerifier.verify(idToken);

        // 2) 우리 서비스 회원 찾기/없으면 가입
        Member member = memberService.findOrCreateKakaoMember(
                kakaoUser.providerId(),
                kakaoUser.nickname(),
                kakaoUser.picture()
        );

        // 3) 닉네임/이미지 변동 시 업데이트(동기화)
        member.updateInfo(
                kakaoUser.nickname() != null ? kakaoUser.nickname() : member.getUsername(),
                kakaoUser.picture() != null ? kakaoUser.picture() : member.getProfileImage()
        );

        // 4) 우리 서비스용 액세스/리프레시 토큰 발급
        return tokenService.createLoginToken(member);
    }
}
