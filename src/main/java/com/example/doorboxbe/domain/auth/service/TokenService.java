package com.example.doorboxbe.domain.auth.service;

import com.example.doorboxbe.domain.auth.dto.response.AuthResponseDTO;
import com.example.doorboxbe.domain.member.entity.Member;

public interface TokenService {
    AuthResponseDTO.LoginResponse createLoginToken(Member member);

    void deleteToken(Member member);

}
