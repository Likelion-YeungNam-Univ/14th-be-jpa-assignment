package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Project;
import lombok.Getter;

@Getter
public class ProjectResponseDto {

    private final Long id;
    private final String projectName;

    public ProjectResponseDto(Project project) {
        this.id = project.getId();
        this.projectName = project.getProjectName();
    }
}
