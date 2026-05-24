package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
