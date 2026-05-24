package yu.likelion14th._th.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th._th.domain.Priority;
import yu.likelion14th._th.dto.priority.PriorityReqDto;
import yu.likelion14th._th.dto.priority.PriorityResDto;
import yu.likelion14th._th.repository.PriorityRepository;

import java.util.List;

// 우선순위 서비스
@Service
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    // 우선순위 생성
    public PriorityResDto create(PriorityReqDto requestDto) {
        Priority priority = Priority.builder()
                .levelName(requestDto.getLevelName())
                .build();
        return PriorityResDto.from(priorityRepository.save(priority));
    }

    // 전체 우선순위 조회
    public List<PriorityResDto> findAll() {
        return priorityRepository.findAll().stream()
                .map(PriorityResDto::from)
                .toList();
    }

    // 단일 우선순위 조회
    public PriorityResDto findById(Long priorityId) {
        Priority priority = priorityRepository.findById(priorityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 우선순위 없음!"));
        return PriorityResDto.from(priority);
    }

    // 우선순위 수정
    public PriorityResDto update(Long priorityId, PriorityReqDto requestDto) {
        Priority priority = priorityRepository.findById(priorityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 우선순위 없음!"));
        priority.update(requestDto.getLevelName());
        return PriorityResDto.from(priority);
    }

    // 우선순위 삭제
    public void delete(Long priorityId) {
        if (!priorityRepository.existsById(priorityId)) {
            throw new IllegalArgumentException("해당 우선순위 없음!");
        }
        priorityRepository.deleteById(priorityId);
    }
}
