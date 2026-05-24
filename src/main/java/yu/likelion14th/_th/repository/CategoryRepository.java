package yu.likelion14th._th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yu.likelion14th._th.domain.Category;

// 카테고리 레포지토리
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
