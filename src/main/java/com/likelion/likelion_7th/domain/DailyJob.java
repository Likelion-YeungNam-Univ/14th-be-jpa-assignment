package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // job_id와 implement를 접근
public class DailyJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long job_id;

    private String content;
    private boolean implement;

    @Builder
    public DailyJob(String content, boolean implement){
        this.content = content;
        this.implement = implement;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "job", cascade = CascadeType.ALL) // OneToOne인데 어케 받지? - 일반 객체 타입 선언
    private JobCalendar jobCalendar;

}
