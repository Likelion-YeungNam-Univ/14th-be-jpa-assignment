package com.likelion.likelion_7th.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DeadlineReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deadline_id")
    private Long id;

    @Column(name = "message", nullable = false, length = 255)
    private String message;

    @Column(name = "reminder_time", nullable = false)
    private LocalDateTime reminderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "deadlineReminder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

}
