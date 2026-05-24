package yu.likelion14th._th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// 우선순위 엔티티
@Entity
@Table(name = "priority")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Priority(String levelName) {
        this.levelName = levelName;
    }

    // 우선순위 수정
    public void update(String levelName) {
        if (levelName != null) this.levelName = levelName;
    }
}
