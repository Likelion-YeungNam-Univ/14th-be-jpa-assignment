package yu.likelion14th._th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 할 일 엔티티
@Entity
@Table(name = "todo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone = false;

    // Todo : User = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Todo : Category = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Todo : Priority = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    // Todo : Reminder = 1 : 1
    @OneToOne(mappedBy = "todo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Reminder reminder;

    @Builder
    public Todo(String content, User user, Category category, Priority priority) {
        this.content = content;
        this.isDone = false;
        this.user = user;
        this.category = category;
        this.priority = priority;
    }

    // 할 일 수정
    public void update(String content, Category category, Priority priority) {
        if (content != null) this.content = content;
        if (category != null) this.category = category;
        if (priority != null) this.priority = priority;
    }

    // 완료 상태 토글
    public void toggleDone() {
        this.isDone = !this.isDone;
    }
}
