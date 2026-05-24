package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Subjects;
import com.likelion.likelion_7th.dto.response.SubjectsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
    default List<SubjectsResponse> findSubjectsResponseList() {
        return findAll().stream()
                .map(SubjectsResponse::from)
                .toList();
    }

    default Subjects findSubjectsById(Long subjectsId) {
        return findById(subjectsId)
                .orElseThrow(() -> new IllegalArgumentException("과목을 찾을 수 없음"));
    }

    default SubjectsResponse getSubjectsResponse(Long subjectsId) {
        return SubjectsResponse.from(findSubjectsById(subjectsId));
    }
}
