package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Subjects;
import com.likelion.likelion_7th.domain.Task;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.TaskRequest;
import com.likelion.likelion_7th.dto.request.TaskUpdateRequest;
import com.likelion.likelion_7th.dto.response.TaskResponse;
import com.likelion.likelion_7th.repository.SubjectsRepository;
import com.likelion.likelion_7th.repository.TaskRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SubjectsRepository subjectsRepository;

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        User user = userRepository.findUserById(request.getUserId());
        Subjects subjects = subjectsRepository.findSubjectsById(request.getSubjectId());

        Task task = Task.builder()
                .name(request.getName())
                .content(request.getContent())
                .deadline(request.getDeadline())
                .status(request.isStatus())
                .build();
        user.addTask(task);
        subjects.addTask(task);

        Task savedTask = taskRepository.save(task);
        return TaskResponse.from(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTaskList() {
        return taskRepository.findTaskResponseList();
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long taskId) {
        return taskRepository.getTaskResponse(taskId);
    }

    @Transactional
    public TaskResponse updateTask(Long taskId, TaskUpdateRequest request) {
        Task task = taskRepository.findTaskById(taskId);
        task.updateTask(request.getDeadline(), request.isStatus());

        return TaskResponse.from(task);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.delete(taskRepository.findTaskById(taskId));
    }
}
