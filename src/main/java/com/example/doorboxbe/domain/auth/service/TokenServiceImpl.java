package com.example.doorboxbe.domain.auth.service;

import com.example.doorboxbe.domain.auth.dto.response.AuthResponseDTO;
import com.example.doorboxbe.domain.member.entity.Member;
import com.example.doorboxbe.global.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public AuthResponseDTO.LoginResponse createLoginToken(Member member) {
        return AuthResponseDTO.LoginResponse.toLoginResponse(
                jwtTokenUtil.createAccessToken(member), jwtTokenUtil.createRefreshToken(member));
    }

    @Override
    public void deleteToken(Member member) {

    }
}
