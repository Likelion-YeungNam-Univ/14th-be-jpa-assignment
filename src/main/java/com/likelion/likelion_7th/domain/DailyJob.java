package com.likelion.likelion_7th.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DailyJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long job_id;

    private String content;
    private boolean implement;

    @Builder
    public DailyJob(String content, boolean implement) {
        this.content = content;
        this.implement = implement;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "dailyJob", cascade = CascadeType.ALL)
    private JobCalendar jobCalendar;

    void assignUser(User user) {
        this.user = user;
    }

    public void assignJobCalendar(JobCalendar jobCalendar) {
        this.jobCalendar = jobCalendar;
        if (jobCalendar != null) {
            jobCalendar.assignDailyJob(this);
        }
    }

    public void updateImplement(boolean implement) {
        this.implement = implement;
    }
}
