package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Schedule;
import com.likelion.likelion_7th.dto.ScheduleRequestDto;
import com.likelion.likelion_7th.dto.ScheduleResponseDto;
import com.likelion.likelion_7th.exception.ResourceNotFoundException;
import com.likelion.likelion_7th.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = Schedule.builder()
                .scheduleName(requestDto.getScheduleName())
                .scheduleMemo(requestDto.getScheduleMemo())
                .scheduleStart(requestDto.getScheduleStart())
                .scheduleEnd(requestDto.getScheduleEnd())
                .scheduleType(requestDto.getScheduleType())
                .build();
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없습니다: " + id));
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없습니다: " + id));
        schedule.update(requestDto.getScheduleName(), requestDto.getScheduleMemo(),
                requestDto.getScheduleStart(), requestDto.getScheduleEnd(), requestDto.getScheduleType());
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("일정을 찾을 수 없습니다: " + id));
        scheduleRepository.delete(schedule);
    }
}
