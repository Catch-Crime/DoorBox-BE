package com.example.doorboxbe.domain.auth.service;

import com.example.doorboxbe.global.apiPayload.code.status.error.TokenErrorStatus;
import com.example.doorboxbe.global.apiPayload.exception.TokenException;
import com.example.doorboxbe.global.security.data.KakaoOAuthConfigData;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.proc.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.*;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoVerifier {

    private final KakaoOAuthConfigData kakaoConfig;

    // id_token을 검증하고, 유의미한 최소한의 정보만 뽑아서 반환
    public KakaoOidcUser verify(String idToken) {
        System.out.println("[KakaoOidcVerifier] verify 시작");

        try {
            // 1) 카카오 JWK 소스(원격) + 타임아웃/캐시 설정
            var retriever = new DefaultResourceRetriever(2000, 2000, 0);
            JWKSource<SecurityContext> jwkSource =
                    new RemoteJWKSet<>(new URL(kakaoConfig.getPublicKeyUrl()), retriever);

            // 2) JWT 프로세서에 서명 알고리즘(RS256) 키 셀렉터 연결
            ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
            JWSKeySelector<SecurityContext> keySelector =
                    new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, jwkSource);
            jwtProcessor.setJWSKeySelector(keySelector);

            // 3) 처리(=서명 검증 + 파싱)
            JWTClaimsSet claims = jwtProcessor.process(SignedJWT.parse(idToken), null);
            System.out.println("[KakaoOidcVerifier] claims 확인: " + claims.toJSONObject());


            // 4) 표준/필수 클레임 검증
            String iss = claims.getIssuer();
            List<String> audList = claims.getAudience();
            Date exp = claims.getExpirationTime();

            if (!kakaoConfig.getIss().equals(iss)) {
                throw new IllegalArgumentException("Invalid iss");
            }
            if (audList == null || !audList.contains(kakaoConfig.getAppKey())) {
                throw new IllegalArgumentException("Invalid aud(client_id)");
            }
            if (exp == null || exp.toInstant().isBefore(Instant.now())) {
                throw new IllegalArgumentException("Expired id_token");
            }

            // 5) 카카오 사용자 식별자(sub) 및 선택적 프로필/이메일 추출
            String sub = claims.getSubject();                               // 카카오 회원 고유 ID
            String nickname = (String) claims.getClaim("nickname"); // scope에 profile_nickname
            String picture = (String) claims.getClaim("picture");   // scope에 profile_image

            System.out.println("[KakaoOidcVerifier] user 정보: " + sub + ", " + nickname + ", " + picture);

            return new KakaoOidcUser(sub, nickname, picture);

        } catch (Exception e) {
            // 프로젝트의 예외 체계(TokenException 등)에 맞춰 변환
            System.out.println("[KakaoOidcVerifier] verify 실패: " + e.getMessage());
            throw new TokenException(TokenErrorStatus.INVALID_ID_TOKEN);
        }
    }

    // OIDC에서 받아온 최소 사용자 정보
    public record KakaoOidcUser(String providerId, String nickname, String picture) {}
}

