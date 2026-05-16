package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Participant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_pk")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_user_key", nullable = false)
    private ProjectUser projectUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_pk", nullable = false)
    private Schedule schedule;
}
