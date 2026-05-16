package com.likelion.likelion_7th.domain.email_verification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    @Column(nullable = false, length = 255)
    private String email; // 이메일

    @Column(nullable = false, length = 6)
    private String authCode; // 인증코드

    @Column(nullable = false)
    private boolean isVerified = false; // 인증 여부

    @Column(nullable = false)
    private LocalDateTime expiredAt; // 만료 시간
}