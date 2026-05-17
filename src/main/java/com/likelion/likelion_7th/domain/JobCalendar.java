package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JobCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendar_id;

    private LocalDate date;
    private boolean implemented;

    //Builder가 필요한가?
    @Builder
    public JobCalendar(LocalDate date,boolean implemented){
        this.date = date;
        this.implemented = implemented;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private DailyJob dailyJob;
}
