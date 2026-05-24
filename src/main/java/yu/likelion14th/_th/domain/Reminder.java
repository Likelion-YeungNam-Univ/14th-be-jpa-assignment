package yu.likelion14th._th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 알림 엔티티
@Entity
@Table(name = "reminder")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id", nullable = false)
    private Long reminderId;

    @Column(name = "is_sent")
    private Boolean isSent;

    // Reminder : Todo = 1 : 1
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @Builder
    public Reminder(Boolean isSent, Todo todo) {
        this.isSent = isSent != null ? isSent : false;
        this.todo = todo;
    }

    // 알림 발송 상태 변경
    public void updateSentStatus(Boolean isSent) {
        if (isSent != null) this.isSent = isSent;
    }
}
