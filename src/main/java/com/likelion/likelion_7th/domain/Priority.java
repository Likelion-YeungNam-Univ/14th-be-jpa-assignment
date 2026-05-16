package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "priority")
@Getter
@NoArgsConstructor
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id", nullable = false)
    private Long priorityId;

    @Column(name = "level_name", nullable = false, length = 20)
    private String levelName;

    // Priority : Todo = 1 : N
    @OneToMany(mappedBy = "priority", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();
}