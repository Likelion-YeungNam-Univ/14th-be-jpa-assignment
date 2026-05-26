package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Subjects;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.SubjectsRequest;
import com.likelion.likelion_7th.dto.response.SubjectsResponse;
import com.likelion.likelion_7th.repository.SubjectsRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectsService {
    private final SubjectsRepository subjectsRepository;
    private final UserRepository userRepository;

    @Transactional
    public SubjectsResponse createSubjects(SubjectsRequest request) {
        User user = userRepository.findUserById(request.getUserId());

        Subjects subjects = Subjects.builder()
                .subject_name(request.getSubjectName())
                .build();
        user.addSubject(subjects);

        Subjects savedSubjects = subjectsRepository.save(subjects);
        return SubjectsResponse.from(savedSubjects);
    }

    @Transactional(readOnly = true)
    public List<SubjectsResponse> getSubjectsList() {
        return subjectsRepository.findSubjectsResponseList();
    }

    @Transactional(readOnly = true)
    public SubjectsResponse getSubjects(Long subjectsId) {
        return subjectsRepository.getSubjectsResponse(subjectsId);
    }

    @Transactional
    public void deleteSubjects(Long subjectsId) {
        subjectsRepository.delete(subjectsRepository.findSubjectsById(subjectsId));
    }
}
