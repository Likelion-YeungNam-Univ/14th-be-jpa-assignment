package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String subtaskTitle;

    @Column(nullable = false)
    private Boolean subtaskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Builder
    public Subtask(String subtaskTitle, Boolean subtaskStatus, Todo todo) {
        this.subtaskTitle = subtaskTitle;
        this.subtaskStatus = subtaskStatus != null ? subtaskStatus : false;
        this.todo = todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}