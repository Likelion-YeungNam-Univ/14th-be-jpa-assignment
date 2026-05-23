package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Day;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.DayReqDto;
import com.likelion.likelion_7th.dto.DayResDto;
import com.likelion.likelion_7th.repository.DayRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;
    private final UserRepository userRepository;

    public DayResDto create(DayReqDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. ID=" + requestDto.getUserId()));

        Day day = Day.builder()
                .dayStart(requestDto.getDayStart())
                .dayEnd(requestDto.getDayEnd())
                .user(user)
                .build();

        Day savedDay = dayRepository.save(day);
        return DayResDto.from(savedDay);
    }
}