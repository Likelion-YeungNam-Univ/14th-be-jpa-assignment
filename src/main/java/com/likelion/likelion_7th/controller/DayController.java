package com.likelion.likelion_7th.controller;


import com.likelion.likelion_7th.dto.DayReqDto;
import com.likelion.likelion_7th.dto.DayResDto;
import com.likelion.likelion_7th.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/days")
public class DayController {

    private final DayService dayService;

    @PostMapping
    public ResponseEntity<DayResDto> createDay(@RequestBody DayReqDto requestDto) {
        return ResponseEntity.ok(dayService.create(requestDto));
    }
}
