package com.example.doorboxbe.global.security;

import com.example.doorboxbe.domain.member.service.MemberService;
import com.example.doorboxbe.global.security.filter.JwtTokenExceptionFilter;
import com.example.doorboxbe.global.security.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;

    private final String[] allowedUrls = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api/oauth2/login/**",

};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // 추가해야 CORS 설정이 적용됨
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic((AbstractHttpConfigurer::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(allowedUrls).permitAll()            // 위에 정의한 allowedUrls는 인증 필요 없음
                        .anyRequest().authenticated()                        // 그 외는 인증 필요
                )
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)  // 먼저 인증 필터
                .addFilterBefore(jwtTokenExceptionFilter(), JwtTokenFilter.class)  // 그 다음 예외 필터

        ;

        return http.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenUtil, securityContextRepository(), memberService);
    }

    @Bean
    public JwtTokenExceptionFilter jwtTokenExceptionFilter() {
        return new JwtTokenExceptionFilter();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new RequestAttributeSecurityContextRepository();
    }

}

