package yu.likelion14th._th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yu.likelion14th._th.domain.Priority;

// 우선순위 레포지토리
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
