package yu.likelion14th._th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yu.likelion14th._th.domain.Reminder;

import java.util.Optional;

// 알림 레포지토리
@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    Optional<Reminder> findByTodo_TodoId(Long todoId);
    boolean existsByTodo_TodoId(Long todoId);
}
