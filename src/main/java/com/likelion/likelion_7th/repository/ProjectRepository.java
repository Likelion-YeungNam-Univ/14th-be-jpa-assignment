package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
