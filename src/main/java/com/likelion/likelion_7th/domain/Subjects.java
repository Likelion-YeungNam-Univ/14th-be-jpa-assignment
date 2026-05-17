package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // 속성 사용 예정
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_id;

    private String subject_name;

    @Builder
    public Subjects(String subject_name){
        this.subject_name = subject_name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}
