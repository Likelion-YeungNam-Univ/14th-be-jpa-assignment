package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private LocalDate dayStart;

    private LocalDate dayEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public Day(LocalDate dayStart, LocalDate dayEnd, User user) {
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
        todo.setDay(this);
    }
}