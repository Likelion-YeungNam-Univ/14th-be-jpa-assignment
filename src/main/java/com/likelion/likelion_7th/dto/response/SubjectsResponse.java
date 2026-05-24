package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.Subjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectsResponse {
    private Long subjectId;
    private Long userId;
    private String subjectName;

    public static SubjectsResponse from(Subjects subjects) {
        return new SubjectsResponse(
                subjects.getSubject_id(),
                subjects.getUser().getUser_id(),
                subjects.getSubject_name()
        );
    }
}
