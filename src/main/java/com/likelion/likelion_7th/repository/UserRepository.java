package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default List<UserResponse> findUserResponseList() {
        return findAll().stream()
                .map(UserResponse::from)
                .toList();
    }

    default User findUserById(Long userId) {
        return findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없음"));
    }

    default UserResponse getUserResponse(Long userId) {
        return UserResponse.from(findUserById(userId));
    }
}
