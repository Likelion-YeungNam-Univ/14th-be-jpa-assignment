package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone = false;

    // Todo : User = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Todo : Category = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Todo : Priority = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    // Todo : Reminder = 1 : 1 (Reminder가 FK 보유, Todo는 거울 참조)
    @OneToOne(mappedBy = "todo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Reminder reminder;
}