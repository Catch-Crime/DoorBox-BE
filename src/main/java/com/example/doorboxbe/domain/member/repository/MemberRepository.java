package com.example.doorboxbe.domain.member.repository;


import com.example.doorboxbe.domain.member.entity.Member;
import com.example.doorboxbe.domain.member.entity.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByProviderIdAndProviderType(String providerId, ProviderType providerType);

    Optional<Member> findByProviderId(String providerId);
}
