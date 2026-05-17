package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String todoTitle;

    @Column(columnDefinition = "TEXT")
    private String todoContent;

    @Column(nullable = false)
    private Boolean todoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private Day day;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<Subtask> subtasks = new ArrayList<>();

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @Builder
    public Todo(String todoTitle, String todoContent, Boolean todoStatus, Day day) {
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus != null ? todoStatus : false; // 기본값 false 처리
        this.day = day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void addSubtask(Subtask subtask) {
        this.subtasks.add(subtask);
        subtask.setTodo(this);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.setTodo(this);
    }

    // Setter 대신 비즈니스 메서드로 상태 변경
    public void updateStatus(Boolean todoStatus) {
        this.todoStatus = todoStatus;
    }
}
