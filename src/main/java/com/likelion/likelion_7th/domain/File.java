package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "File")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_pk")
    private Long id;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_pk", nullable = false)
    private Post post;
}
