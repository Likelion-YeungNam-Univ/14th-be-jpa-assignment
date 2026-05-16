package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Schedule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_pk")
    private Long id;

    @Column(name = "schedule_name", nullable = false, length = 255)
    private String scheduleName;

    @Column(name = "schedule_memo", length = 255)
    private String scheduleMemo;

    @Column(name = "schedule_start", nullable = false)
    private LocalDateTime scheduleStart;

    @Column(name = "schedule_end", nullable = false)
    private LocalDateTime scheduleEnd;

    @Column(name = "schedule_type", length = 255)
    private String scheduleType;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Participant> participants = new ArrayList<>();
}
