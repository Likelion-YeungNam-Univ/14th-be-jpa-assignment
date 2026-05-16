package com.likelion.likelion_7th.domain;

import com.jayway.jsonpath.internal.function.numeric.Min;
import jakarta.persistence.*;

@Entity
//한 유저가 같은 영화에 별점 1개만 가능
@Table(
        name = "rating",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "movie_id"})
)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 별점 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //별점 준 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; //별점 받은 영화

    @Column(nullable = false)
    private Integer score; //별점 점수
}