package yu.likelion14th._th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yu.likelion14th._th.domain.Todo;

import java.util.List;

// 할 일 레포지토리
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser_UserId(Long userId);
}
