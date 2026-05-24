package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Project;
import com.likelion.likelion_7th.dto.ProjectRequestDto;
import com.likelion.likelion_7th.dto.ProjectResponseDto;
import com.likelion.likelion_7th.exception.ResourceNotFoundException;
import com.likelion.likelion_7th.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto requestDto) {
        Project project = Project.builder()
                .projectName(requestDto.getProjectName())
                .build();
        return new ProjectResponseDto(projectRepository.save(project));
    }

    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProjectResponseDto getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("프로젝트를 찾을 수 없습니다: " + id));
        return new ProjectResponseDto(project);
    }

    @Transactional
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("프로젝트를 찾을 수 없습니다: " + id));
        project.update(requestDto.getProjectName());
        return new ProjectResponseDto(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("프로젝트를 찾을 수 없습니다: " + id));
        projectRepository.delete(project);
    }
}
