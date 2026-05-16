package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "icon_url", nullable = false, length = 255)
    private String iconUrl;

    // Category : Todo = 1 : N
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)

    private List<Todo> todos = new ArrayList<>();
}