package com.likelion.likelion_7th.domain.users.repository;

import com.likelion.likelion_7th.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
