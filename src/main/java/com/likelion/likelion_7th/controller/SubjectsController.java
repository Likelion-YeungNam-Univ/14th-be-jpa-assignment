package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.SubjectsRequest;
import com.likelion.likelion_7th.dto.response.SubjectsResponse;
import com.likelion.likelion_7th.service.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectsController {
    private final SubjectsService subjectsService;

    @PostMapping
    public ResponseEntity<SubjectsResponse> createSubjects(@RequestBody SubjectsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectsService.createSubjects(request));
    }

    @GetMapping
    public ResponseEntity<List<SubjectsResponse>> getSubjectsList() {
        return ResponseEntity.ok(subjectsService.getSubjectsList());
    }

    @GetMapping("/{subjectsId}")
    public ResponseEntity<SubjectsResponse> getSubjects(@PathVariable Long subjectsId) {
        return ResponseEntity.ok(subjectsService.getSubjects(subjectsId));
    }

    @DeleteMapping("/{subjectsId}")
    public ResponseEntity<Void> deleteSubjects(@PathVariable Long subjectsId) {
        subjectsService.deleteSubjects(subjectsId);
        return ResponseEntity.noContent().build();
    }
}
