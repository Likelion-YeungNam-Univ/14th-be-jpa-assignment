package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.SubjectsRequest;
import com.likelion.likelion_7th.dto.response.SubjectsResponse;
import com.likelion.likelion_7th.service.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectsController {
    private final SubjectsService subjectsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectsResponse createSubjects(@RequestBody SubjectsRequest request) {
        return subjectsService.createSubjects(request);
    }

    @GetMapping
    public List<SubjectsResponse> getSubjectsList() {
        return subjectsService.getSubjectsList();
    }

    @GetMapping("/{subjectsId}")
    public SubjectsResponse getSubjects(@PathVariable Long subjectsId) {
        return subjectsService.getSubjects(subjectsId);
    }

    @DeleteMapping("/{subjectsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubjects(@PathVariable Long subjectsId) {
        subjectsService.deleteSubjects(subjectsId);
    }
}
