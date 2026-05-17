package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Builder
    public Category(String categoryName, Todo todo) {
        this.categoryName = categoryName;
        this.todo = todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}
