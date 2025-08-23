package com.example.doorboxbe.domain.member.entity;

import com.example.doorboxbe.domain.member.entity.enums.ProviderType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    private ProviderType providerType;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImage;


    public void updateInfo(String username, String profileImage) {
        this.username = username;
        this.profileImage = profileImage;
    }
}
