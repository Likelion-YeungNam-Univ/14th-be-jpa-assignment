package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.DailyJob;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.DailyJobRequest;
import com.likelion.likelion_7th.dto.request.DailyJobUpdateRequest;
import com.likelion.likelion_7th.dto.response.DailyJobResponse;
import com.likelion.likelion_7th.repository.DailyJobRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyJobService {
    private final DailyJobRepository dailyJobRepository;
    private final UserRepository userRepository;

    @Transactional
    public DailyJobResponse createDailyJob(DailyJobRequest request) {
        User user = userRepository.findUserById(request.getUserId());

        DailyJob dailyJob = DailyJob.builder()
                .content(request.getContent())
                .implement(request.isImplement())
                .build();
        user.addDailyJob(dailyJob);

        DailyJob savedDailyJob = dailyJobRepository.save(dailyJob);
        return DailyJobResponse.from(savedDailyJob);
    }

    @Transactional(readOnly = true)
    public List<DailyJobResponse> getDailyJobList() {
        return dailyJobRepository.findDailyJobResponseList();
    }

    @Transactional(readOnly = true)
    public DailyJobResponse getDailyJob(Long dailyJobId) {
        return dailyJobRepository.getDailyJobResponse(dailyJobId);
    }

    @Transactional
    public DailyJobResponse updateDailyJob(Long dailyJobId, DailyJobUpdateRequest request) {
        DailyJob dailyJob = dailyJobRepository.findDailyJobById(dailyJobId);
        dailyJob.updateImplement(request.isImplement());

        return DailyJobResponse.from(dailyJob);
    }

    @Transactional
    public void deleteDailyJob(Long dailyJobId) {
        dailyJobRepository.delete(dailyJobRepository.findDailyJobById(dailyJobId));
    }
}
